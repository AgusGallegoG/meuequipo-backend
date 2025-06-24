package com.web.meuequipo.core.team.rest;

import com.web.meuequipo.core.shared.dto.response.MatchTeamDTO;
import com.web.meuequipo.core.team.dto.request.TeamSaveRequest;
import com.web.meuequipo.core.team.dto.response.TeamDetailsResponse;
import com.web.meuequipo.core.team.dto.response.TeamItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamMenuItemResponse;
import com.web.meuequipo.core.team.dto.response.TeamPublicResponse;
import com.web.meuequipo.core.team.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public Page<TeamItemResponse> getTeamsPage(Pageable pageable) {
        return teamService.getTeams(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TeamDetailsResponse> getTeamDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teamService.getTeamDetails(id));
    }

    @GetMapping("/match-team")
    @PreAuthorize("isAuthenticated()")
    List<MatchTeamDTO> getMatchTeamByCateogry(@RequestParam(name = "categoryId") Long categoryId) {
        return teamService.getTeamsToMatchByCategory(categoryId);
    }

    @GetMapping("/public/{id}")
    ResponseEntity<TeamPublicResponse> getTeamPublic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teamService.getTeamPublic(id));
    }

    @GetMapping("/public/menu")
    List<TeamMenuItemResponse> getTeamsForMenu() {
        return teamService.getTeamMenuItems();
    }

    @PostMapping()
    ResponseEntity<TeamItemResponse> saveTeam(@RequestBody TeamSaveRequest teamSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.saveTeam(teamSaveRequest));
    }


}
