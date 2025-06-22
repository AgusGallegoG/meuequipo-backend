package com.web.meuequipo.core.shared.service;

import com.web.meuequipo.core.shared.dto.SelectDTO;
import com.web.meuequipo.core.shared.enums.MatchState;
import com.web.meuequipo.core.shared.enums.Sex;
import com.web.meuequipo.core.shared.enums.SexPlayers;
import com.web.meuequipo.core.shared.enums.SigninState;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnumsServiceImpl implements EnumsService {


    @Override
    public List<SelectDTO> getSex() {
        return Arrays.stream(Sex.values())
                .map(s ->
                        new SelectDTO(s.getId(), s.getLabel())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<SelectDTO> getSexPlayers() {
        return Arrays.stream(SexPlayers.values())
                .map(p ->
                        new SelectDTO(p.getId(), p.getLabel())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<SelectDTO> getSigninState() {
        return Arrays.stream(SigninState.values())
                .map(s ->
                        new SelectDTO(s.getId(), s.getLabel())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<SelectDTO> getMatchState() {
        return Arrays.stream(MatchState.values())
                .map(m ->
                        new SelectDTO(m.getId(), m.getLabel())
                )
                .collect(Collectors.toList());
    }
}
