package com.web.meuequipo.core.shared.service;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;

import java.util.List;

public interface EnumsService {
    List<SelectDTO> getSex();

    List<SelectDTO> getSexPlayers();

    List<SelectDTO> getSigninState();

    List<SelectDTO> getGameState();
}
