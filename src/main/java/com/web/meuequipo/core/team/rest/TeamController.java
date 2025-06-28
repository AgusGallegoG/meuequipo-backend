package com.web.meuequipo.core.team.rest;

import com.web.meuequipo.core.shared.dto.response.GameTeamResponse;
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
    public Page<TeamItemResponse> getTeamsPage(Pageable pageable) {
        return teamService.getTeams(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDetailsResponse> getTeamDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teamService.getTeamDetails(id));
    }

    @GetMapping("/game-team")
    List<GameTeamResponse> getGameTeamByCateogry(@RequestParam(name = "categoryId") Long categoryId) {
        return teamService.getTeamsToGameByCategory(categoryId);
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
