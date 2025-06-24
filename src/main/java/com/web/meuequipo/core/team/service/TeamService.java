package com.web.meuequipo.core.team.service;

import com.web.meuequipo.core.shared.dto.response.MatchTeamDTO;
import com.web.meuequipo.core.team.dto.request.TeamSaveRequest;
import com.web.meuequipo.core.team.dto.response.TeamDetailsResponse;
import com.web.meuequipo.core.team.dto.response.TeamItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamMenuItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamPublicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamService {

    TeamDetailsResponse getTeamDetails(Long id);

    TeamPublicResponse getTeamPublic(Long id);

    Page<TeamItemResponse> getTeams(Pageable pageable);

    TeamItemResponse saveTeam(TeamSaveRequest teamSaveRequest);

    List<MatchTeamDTO> getTeamsToMatchByCategory(Long categoryId);

    List<TeamMenuItemResponse> getTeamMenuItems();
}
