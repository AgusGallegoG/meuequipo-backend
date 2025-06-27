package com.web.meuequipo.core.player.service;

import com.web.meuequipo.core.player.dto.request.PlayerRequest;
import com.web.meuequipo.core.player.dto.response.PlayerResponse;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    List<SelectDTO> getFreePlayersByCategory(Long categoryId);

    List<SelectDTO> getPlayersByTeam(Long idTeam);

    PlayerResponse savePlayer(PlayerRequest request);

    Page<PlayerResponse> getPlayers(Pageable pageable, Long categoryId, Long sex);
}
