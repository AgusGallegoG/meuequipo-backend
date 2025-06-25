package com.web.meuequipo.core.game.service;

import com.web.meuequipo.core.game.dto.request.GameRequestFilters;
import com.web.meuequipo.core.game.dto.request.GameSaveRequest;
import com.web.meuequipo.core.game.dto.response.GameResponse;

import java.util.List;

public interface GameService {
    List<GameResponse> getCalendarAdmin(GameRequestFilters filters);

    List<GameResponse> getCalendarPublic(GameRequestFilters filters);

    GameResponse saveGame(GameSaveRequest gameSaveRequest);
}
