package com.web.meuequipo.core.shared.rest;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.shared.service.EnumsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enums")
public class EnumsController {

    private final EnumsService enumsService;

    public EnumsController(EnumsService enumsService) {
        this.enumsService = enumsService;
    }

    @GetMapping("/sex")
    public List<SelectDTO> getSexEnum() {
        return this.enumsService.getSex();
    }

    @GetMapping("/sexPlayers")
    public List<SelectDTO> getSexPlayers() {
        return this.enumsService.getSexPlayers();
    }

    @GetMapping("/signinState")
    public List<SelectDTO> getSigninState() {
        return this.enumsService.getSigninState();
    }

    @GetMapping("/gameStates")
    public List<SelectDTO> getGameStates() {
        return this.enumsService.getGameState();
    }


}
