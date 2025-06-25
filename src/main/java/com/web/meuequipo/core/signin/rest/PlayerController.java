package com.web.meuequipo.core.signin.rest;


import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.signin.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@PreAuthorize("isAuthenticated()")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/category")
    List<SelectDTO> getFreePlayersByCategory(@RequestParam(name = "idCategory") Long idCategory) {
        return playerService.getFreePlayersByCategory(idCategory);
    }

}
