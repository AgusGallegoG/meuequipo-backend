package com.web.meuequipo.core.game.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.game.Game;
import com.web.meuequipo.core.game.data.GameRepository;
import com.web.meuequipo.core.game.dto.request.GameRequestFilters;
import com.web.meuequipo.core.game.dto.request.GameSaveRequest;
import com.web.meuequipo.core.game.dto.response.GameResponse;
import com.web.meuequipo.core.game.exception.GameException;
import com.web.meuequipo.core.game.util.GameUtil;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.rival.data.RivalRepository;
import com.web.meuequipo.core.rival.exception.RivalException;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.enums.GameState;
import com.web.meuequipo.core.team.Team;
import com.web.meuequipo.core.team.data.TeamRepository;
import com.web.meuequipo.core.team.exception.TeamException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final SeasonRepository seasonRepository;

    private final RivalRepository rivalRepository;

    private final TeamRepository teamRepository;

    private final CategoryRepository categoryRepository;


    public GameServiceImpl(GameRepository gameRepository, SeasonRepository seasonRepository, TeamRepository teamRepository,
                           CategoryRepository categoryRepository, RivalRepository rivalRepository) {
        this.gameRepository = gameRepository;
        this.seasonRepository = seasonRepository;
        this.teamRepository = teamRepository;
        this.categoryRepository = categoryRepository;
        this.rivalRepository = rivalRepository;
    }

    /**
     * Si isSquad es true -> Queremos recuperar los games que no tienen squad asignada,
     * estamos en la pantalla de creacion de squads
     * Si isSquad es false -> Somos admin en este contexto y mandamos el squad por si
     * se quiere ver.
     */
    @Override
    public List<GameResponse> getCalendarAdmin(GameRequestFilters filters) {
        List<Game> games = gameRepository.findAllByFilters(filters.getFrom().atStartOfDay(), filters.getTo().atStartOfDay(),
                filters.getTeam(), null, null);

        if (filters.getIsSquad()) {
            return games.stream()
                    .filter(game -> game.getSquad() == null)
                    .map(GameUtil::mapGameToGameResponseWithoutSquad)
                    .toList();
        } else {
            return games.stream().map(GameUtil::mapGameToGameResponseWithSquad).toList();
        }
    }

    /**
     * Contexto public, no mandamos informacion de squad, tiene datos sensibles
     */
    @Override
    public List<GameResponse> getCalendarPublic(GameRequestFilters filters) {
        List<Game> games = gameRepository.findAllByFilters(filters.getFrom().atStartOfDay(), filters.getTo().atStartOfDay(),
                filters.getTeam(), GameState.PRIVATE.getId(), GameState.CANCELED.getId());

        return games.stream().map(GameUtil::mapGameToGameResponseWithoutSquad).toList();
    }

    @Override
    @Transactional
    public GameResponse saveGame(GameSaveRequest gameSaveRequest) {
        Game saved;
        if (gameSaveRequest.getId() == null) {
            saved = createGame(gameSaveRequest);
        } else {
            saved = updateGame(gameSaveRequest);
        }
        return GameUtil.mapGameToGameResponseWithoutSquad(saved);
    }

    private Game createGame(GameSaveRequest gameSaveRequest) {
        Game game = new Game();

        game.setSeason(this.getActiveSeason());

        mapGameEntity(gameSaveRequest, game);

        return gameRepository.save(game);
    }

    private Game updateGame(GameSaveRequest gameSaveRequest) {
        Game game = gameRepository.findById(gameSaveRequest.getId())
                .orElseThrow(() -> new GameException("Non se atopou o partido con id: " + gameSaveRequest.getId()));

        mapGameEntity(gameSaveRequest, game);

        return gameRepository.save(game);
    }

    private void mapGameEntity(GameSaveRequest gameSaveRequest, Game game) {
        manageTeamAndRivalAssign(gameSaveRequest, game);

        game.setLocalPoints(gameSaveRequest.getLocalPoints());
        game.setVisitorPoints(gameSaveRequest.getVisitorPoints());
        game.setCategory(this.getCategory(gameSaveRequest.getCategory()));
        game.setGameDate(gameSaveRequest.getGameDate());
        game.setLocation(gameSaveRequest.getLocation());
        game.setGameState(gameSaveRequest.getState());
    }

    private void manageTeamAndRivalAssign(GameSaveRequest gameSaveRequest, Game game) {

        Boolean localOurs = gameSaveRequest.getLocalTeam() != null && gameSaveRequest.getLocalTeam().getIsOurTeam();
        Boolean visitorOurs = gameSaveRequest.getVisitorTeam() != null && gameSaveRequest.getVisitorTeam().getIsOurTeam();

        if (localOurs && visitorOurs) {
            throw new IllegalArgumentException("Non se poden enfrentar dous equipos propios");
        }

        if (gameSaveRequest.getLocalTeam() != null) {
            game.setTeam(this.getTeam(gameSaveRequest.getLocalTeam().getId()));
        }

        if (gameSaveRequest.getVisitorTeam() != null) {
            game.setRival(this.getRival(gameSaveRequest.getVisitorTeam().getId()));
        }

        if (localOurs) {
            game.setIsLocal(Boolean.TRUE);
        } else {
            game.setIsLocal(Boolean.FALSE);
        }
    }

    private Team getTeam(Long id) {
        return teamRepository.findTeamByIdOfActualSeason(id)
                .orElseThrow(() -> new TeamException("Non se atopu o Team co id: " + id));
    }

    private Rival getRival(Long id) {
        return rivalRepository.findRivalByIdOfActualSeason(id)
                .orElseThrow(() -> new RivalException("Non se atopu o Rival co id: " + id));
    }

    private Category getCategory(Long id) {
        return categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(id)
                .orElseThrow(() -> new CategoryException("Non se atopu o Category co id: " + id));
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }
}
