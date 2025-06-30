package com.web.meuequipo.core.team.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.player.Player;
import com.web.meuequipo.core.player.data.PlayerRepository;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.dto.response.GameTeamResponse;
import com.web.meuequipo.core.team.Team;
import com.web.meuequipo.core.team.data.TeamRepository;
import com.web.meuequipo.core.team.dto.request.TeamSaveRequest;
import com.web.meuequipo.core.team.dto.response.TeamDetailsResponse;
import com.web.meuequipo.core.team.dto.response.TeamItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamMenuItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamPublicResponse;
import com.web.meuequipo.core.team.exception.TeamException;
import com.web.meuequipo.core.team.util.TeamUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final SeasonRepository seasonRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, SeasonRepository seasonRepository, CategoryRepository categoryRepository, ImageRepository imageRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.seasonRepository = seasonRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public TeamDetailsResponse getTeamDetails(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamException("Non se atopou equipo con id: " + id));
        return TeamUtil.mapTeamToResponseTeamDetails(team);
    }

    @Override
    public TeamPublicResponse getTeamPublic(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamException("Non se atopou equipo con id: " + id));
        return TeamUtil.mapTeamToResponseTeamPublic(team);
    }

    @Override
    public Page<TeamItemResponse> getTeams(Pageable pageable) {
        Page<Team> teams = teamRepository.findTeamsOfActualSeason(pageable);

        return teams.map(TeamUtil::mapTeamToTeamItemResponse);
    }

    @Override
    public List<GameTeamResponse> getTeamsToGameByCategory(Long categoryId) {
        List<Team> teams = teamRepository.findTeamsByCategoryIdOfActualSeason(categoryId);

        return teams.stream().map(TeamUtil::mapTeamToResponseGameTeamResponse).toList();
    }

    @Override
    public List<TeamMenuItemResponse> getTeamMenuItems() {
        List<Team> teams = teamRepository.findTeamsOfActualSeason();
        Map<Category, List<Team>> teamsByCategory = teams.stream().collect(Collectors.groupingBy(Team::getCategory));

        return teamsByCategory.entrySet().stream()
                .map(c -> TeamUtil.mapTeamToResponseTeamMenuItem(c.getKey(), c.getValue()))
                .toList();
    }

    @Override
    @Transactional
    public TeamItemResponse saveTeam(TeamSaveRequest teamSaveRequest) {
        Team saved;

        if (teamSaveRequest.getId() != null) {
            saved = this.updateTeam(teamSaveRequest);
        } else {
            saved = this.createTeam(teamSaveRequest);
        }

        return TeamUtil.mapTeamToTeamItemResponse(saved);
    }

    private Team updateTeam(TeamSaveRequest teamSaveRequest) {
        Team team = teamRepository.findById(teamSaveRequest.getId())
                .orElseThrow(() -> new TeamException("Non se atopou equipo con id: " + teamSaveRequest.getId()));


        return saveTeamEntity(teamSaveRequest, team);
    }

    private Team createTeam(TeamSaveRequest teamSaveRequest) {
        Team team = new Team();

        team.setSeason(this.getActiveSeason());

        return saveTeamEntity(teamSaveRequest, team);
    }

    private Team saveTeamEntity(TeamSaveRequest teamSaveRequest, Team team) {
        team.setName(teamSaveRequest.getName());
        team.setTrainer(teamSaveRequest.getTrainer());
        team.setTrainerContact(teamSaveRequest.getTrainerContact());
        team.setSex(teamSaveRequest.getSex());

        if (teamSaveRequest.getCategory() != null) {
            team.setCategory(this.getCategory(teamSaveRequest.getCategory()));
        } else {
            throw new IllegalArgumentException("Non se asociou o equipo a unha categoria.");
        }

        if (teamSaveRequest.getTeamImage() != null) {
            team.setTeamImage(this.getImage(teamSaveRequest.getTeamImage().getId()));
        }

        if (teamSaveRequest.getPlayers() != null && !teamSaveRequest.getPlayers().isEmpty()) {
            updatePlayers(team, teamSaveRequest.getPlayers());
        }

        return teamRepository.save(team);
    }


    private void updatePlayers(Team team, List<Long> playerIds) {

        List<Player> newPlayers = playerRepository.findAllByIdOfActualSeason(playerIds);

        if (newPlayers.isEmpty() || newPlayers.size() != playerIds.size()) {
            throw new TeamException("O numero de xogadores asociados non coincide cos obtidos en base de datos");
        }

        //hacemos una copia para evitar problemas
        List<Player> actualPlayers = new ArrayList<>(team.getPlayers());

        for (Player player : actualPlayers) {
            team.removePlayer(player);
        }

        for (Player player : newPlayers) {
            team.addPlayer(player);
        }
    }

    private Category getCategory(Long id) {
        return categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(id)
                .orElseThrow(() -> new CategoryException("Non se atopu o Category co id: " + id));
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }

    private Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + id));
    }
}
