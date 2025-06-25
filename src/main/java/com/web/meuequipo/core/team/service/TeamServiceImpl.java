package com.web.meuequipo.core.team.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.shared.dto.response.GameTeamDTO;
import com.web.meuequipo.core.signin.Player;
import com.web.meuequipo.core.signin.data.PlayerRepository;
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
    public List<GameTeamDTO> getTeamsToGameByCategory(Long categoryId) {
        List<Team> teams = teamRepository.findTeamsByCategoryIdOfActualSeason(categoryId);

        return teams.stream().map(TeamUtil::mapTeamToResponseGameTeamDTO).toList();
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
            saved = this.createTeam(teamSaveRequest);
        } else {
            saved = this.updateTeam(teamSaveRequest);
        }

        return TeamUtil.mapTeamToTeamItemResponse(saved);
    }

    private Team updateTeam(TeamSaveRequest teamSaveRequest) {
        Team team = teamRepository.findById(teamSaveRequest.getId())
                .orElseThrow(() -> new TeamException("Non se atopou equipo con id: " + teamSaveRequest.getId()));


        return saveTeamEntity(teamSaveRequest, team);
    }

    private Team createTeam(TeamSaveRequest teamSaveRequest) {
        Season season = this.seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new IllegalStateException("Non se atopou unha temporada activa"));

        Team team = new Team();

        team.setSeason(season);

        return saveTeamEntity(teamSaveRequest, team);
    }

    private Team saveTeamEntity(TeamSaveRequest teamSaveRequest, Team team) {
        team.setName(teamSaveRequest.getName());
        team.setTrainer(teamSaveRequest.getTrainer());
        team.setTrainerContact(teamSaveRequest.getTrainerContact());

        if (teamSaveRequest.getCategory() != null) {
            Category category = categoryRepository.findCategoryByIdAndIsActiveTrue(teamSaveRequest.getCategory())
                    .orElseThrow(() -> new CategoryException("Non se atopou unha categoria co id " + teamSaveRequest.getCategory()));

            team.setCategory(category);
        } else {
            throw new IllegalArgumentException("Non se asociou o equipo a unha categoria.");
        }

        if (teamSaveRequest.getTeamImage() != null) {
            Image image = imageRepository.findById(teamSaveRequest.getTeamImage().getId())
                    .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + teamSaveRequest.getTeamImage().getId()));
            team.setTeamImage(image);
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
}
