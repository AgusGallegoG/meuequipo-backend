package com.web.meuequipo.core.shared.service;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.shared.enums.GameState;
import com.web.meuequipo.core.shared.enums.Sex;
import com.web.meuequipo.core.shared.enums.SexPlayers;
import com.web.meuequipo.core.shared.enums.SigninState;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumsServiceImpl implements EnumsService {


    @Override
    public List<SelectDTO> getSex() {
        return Arrays.stream(Sex.values())
                .map(s ->
                        new SelectDTO(s.getId(), s.getLabel())
                )
                .toList();
    }

    @Override
    public List<SelectDTO> getSexPlayers() {
        return Arrays.stream(SexPlayers.values())
                .map(p ->
                        new SelectDTO(p.getId(), p.getLabel())
                )
                .toList();
    }

    @Override
    public List<SelectDTO> getSigninState() {
        return Arrays.stream(SigninState.values())
                .map(s ->
                        new SelectDTO(s.getId(), s.getLabel())
                )
                .toList();
    }

    @Override
    public List<SelectDTO> getGameState() {
        return Arrays.stream(GameState.values())
                .map(m ->
                        new SelectDTO(m.getId(), m.getLabel())
                )
                .toList();
    }
}
