package com.web.meuequipo.core.player.rest;


import com.web.meuequipo.core.player.dto.request.PlayerRequest;
import com.web.meuequipo.core.player.dto.response.PlayerResponse;
import com.web.meuequipo.core.player.service.PlayerService;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/category/{id}")
    List<SelectDTO> getFreePlayersByCategory(@PathVariable(name = "id") Long idCategory) {
        return playerService.getFreePlayersByCategory(idCategory);
    }

    @GetMapping("/team/{id}")
    List<SelectDTO> getPlayersByTeam(@PathVariable(name = "id") Long idTeam) {
        return playerService.getPlayersByTeam(idTeam);
    }

    @PostMapping("/admin")
    ResponseEntity<PlayerResponse> savePlayer(@RequestBody PlayerRequest playerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.savePlayer(playerRequest));
    }

    @GetMapping("/admin")
    Page<PlayerResponse> getPlayers(Pageable pageable,
                                    @RequestParam(name = "categoryId", required = false) Long categoryId,
                                    @RequestParam(name = "sex", required = false) Long sex) {
        return playerService.getPlayers(pageable, categoryId, sex);
    }
}
