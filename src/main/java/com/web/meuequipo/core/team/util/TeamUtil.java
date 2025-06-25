package com.web.meuequipo.core.team.util;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.image.util.ImageUtil;
import com.web.meuequipo.core.shared.dto.response.GameTeamDTO;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.signin.Player;
import com.web.meuequipo.core.team.Team;
import com.web.meuequipo.core.team.dto.response.TeamDetailsResponse;
import com.web.meuequipo.core.team.dto.response.TeamItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamMenuItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamPublicResponse;

import java.util.List;

public class TeamUtil {

    public static TeamDetailsResponse mapTeamToResponseTeamDetails(Team team) {
        TeamDetailsResponse teamDetailsResponse = new TeamDetailsResponse();

        teamDetailsResponse.setId(team.getId());
        teamDetailsResponse.setName(team.getName());
        teamDetailsResponse.setSex(team.getSex());
        teamDetailsResponse.setCategory(team.getCategory().getId());
        teamDetailsResponse.setTrainer(team.getTrainer());
        teamDetailsResponse.setTrainerContact(team.getTrainerContact());
        teamDetailsResponse.setPlayers(team.getPlayers().stream().map(Player::getId).toList());

        if (team.getTeamImage() != null) {
            teamDetailsResponse.setTeamImage(ImageUtil.getImageViewDTO(team.getTeamImage()));
        }

        return teamDetailsResponse;
    }

    public static TeamPublicResponse mapTeamToResponseTeamPublic(Team team) {
        TeamPublicResponse teamPublicResponse = new TeamPublicResponse();

        teamPublicResponse.setId(team.getId());
        teamPublicResponse.setName(team.getName());
        teamPublicResponse.setCategory(team.getCategory().getId());
        teamPublicResponse.setSex(team.getSex());

        if (team.getTeamImage() != null) {
            teamPublicResponse.setTeamImage(ImageUtil.getImageViewDTO(team.getTeamImage()));
        }

        return teamPublicResponse;
    }

    public static TeamItemResponse mapTeamToTeamItemResponse(Team team) {
        TeamItemResponse teamItemResponse = new TeamItemResponse();

        teamItemResponse.setId(team.getId());
        teamItemResponse.setName(team.getName());
        teamItemResponse.setCategory(team.getCategory().getId());
        teamItemResponse.setSex(team.getSex());
        teamItemResponse.setTrainer(team.getTrainer());
        teamItemResponse.setPlayerCount(team.countPlayers());

        return teamItemResponse;
    }

    public static GameTeamDTO mapTeamToResponseGameTeamDTO(Team team) {
        GameTeamDTO gameTeamDTO = new GameTeamDTO();

        gameTeamDTO.setId(team.getId());
        gameTeamDTO.setName(team.getName());
        gameTeamDTO.setIsOurTeam(true);
        if (team.getTeamImage() != null) {
            gameTeamDTO.setLogo(ImageUtil.getImageViewDTO(team.getTeamImage()));
        }

        return gameTeamDTO;
    }

    public static TeamMenuItemResponse mapTeamToResponseTeamMenuItem(Category category, List<Team> teams) {
        List<SelectDTO> teamsAsSelect = teams.stream().map(team -> new SelectDTO(team.getId(), team.getName())).toList();
        return new TeamMenuItemResponse(category.getId(), category.getName(), teamsAsSelect);
    }
}
