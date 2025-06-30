package com.web.meuequipo.core.player.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.player.Player;
import com.web.meuequipo.core.player.data.PlayerRepository;
import com.web.meuequipo.core.player.dto.request.PlayerRequest;
import com.web.meuequipo.core.player.dto.response.PlayerResponse;
import com.web.meuequipo.core.player.exception.PlayerException;
import com.web.meuequipo.core.player.util.PlayerUtil;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final CategoryRepository categoryRepository;

    private final SeasonRepository seasonRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, CategoryRepository categoryRepository, SeasonRepository seasonRepository) {
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.seasonRepository = seasonRepository;
    }


    @Override
    public List<SelectDTO> getFreePlayersByCategory(Long categoryId) {
        List<Player> players = playerRepository.findAllFreeByCategory(categoryId);

        return players.stream().map(PlayerUtil::mapPlayerToSelectDTO).toList();
    }

    @Override
    public List<SelectDTO> getPlayersByTeam(Long idTeam) {
        List<Player> players = playerRepository.findAllOfTeam(idTeam);

        return players.stream().map(PlayerUtil::mapPlayerToSelectDTO).toList();
    }

    @Override
    public Page<PlayerResponse> getPlayers(Pageable pageable, Long categoryId, Long sex) {
        Page<Player> players = playerRepository.findPlayersByFilter(pageable, categoryId, sex);

        return players.map(PlayerUtil::mapPlayerToResponsePlayer);
    }

    @Override
    @Transactional
    public PlayerResponse savePlayer(PlayerRequest request) {
        Player player;

        if (request.getId() != null) {
            player = updatePlayer(request);
        } else {
            player = createPlayer(request);
        }

        return PlayerUtil.mapPlayerToResponsePlayer(player);
    }

    private Player createPlayer(PlayerRequest request) {
        Player player = new Player();

        this.mapPlayerEntity(player, request);
        player.setSeason(this.getActiveSeason());

        return playerRepository.save(player);
    }

    private Player updatePlayer(PlayerRequest request) {
        Player player = playerRepository.findPlayerByIdOfActualSeason(request.getId())
                .orElseThrow(() -> new PlayerException("Non se atopou o xogador con id: " + request.getId()));

        this.mapPlayerEntity(player, request);

        return playerRepository.save(player);
    }

    private void mapPlayerEntity(Player player, PlayerRequest request) {
        player.setName(request.getName());
        player.setSurnames(request.getSurnames());
        player.setPlayerSex(request.getSex());
        player.setBirthDate(request.getBirthDate());
        player.setCategory(this.getCategory(request.getCategory()));

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
