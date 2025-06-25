package com.web.meuequipo.core.game.rest;

import com.web.meuequipo.core.game.dto.request.GameRequestFilters;
import com.web.meuequipo.core.game.dto.request.GameSaveRequest;
import com.web.meuequipo.core.game.dto.response.GameResponse;
import com.web.meuequipo.core.game.service.GameService;
import com.web.meuequipo.core.squad.dto.request.SquadCreateRequest;
import com.web.meuequipo.core.squad.dto.response.SquadResponse;
import com.web.meuequipo.core.squad.service.SquadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
public class GameController {

    private final GameService gameService;
    private final SquadService squadService;

    public GameController(GameService gameService, SquadService squadService) {
        this.gameService = gameService;
        this.squadService = squadService;
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public List<GameResponse> getGamesAdminContext(@ModelAttribute GameRequestFilters gameRequestFilters) {
        return gameService.getCalendarAdmin(gameRequestFilters);
    }

    @GetMapping("/public")
    public List<GameResponse> getGamesPublicContext(@ModelAttribute GameRequestFilters gameRequestFilters) {
        return gameService.getCalendarPublic(gameRequestFilters);
    }

    @PostMapping("/game")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GameResponse> saveGame(@RequestBody GameSaveRequest gameSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.saveGame(gameSaveRequest));
    }

    @PostMapping("/game/squad")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SquadResponse> createSquad(@RequestBody SquadCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(squadService.create(request));
    }
}
