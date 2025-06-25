package com.web.meuequipo.core.squad.util;

import com.web.meuequipo.core.signin.util.PlayerUtil;
import com.web.meuequipo.core.squad.Squad;
import com.web.meuequipo.core.squad.dto.response.SquadResponse;


public class SquadUtil {

    public static SquadResponse createSquadResponse(Squad squad) {
        SquadResponse squadResponse = new SquadResponse();

        squadResponse.setId(squad.getId());
        squadResponse.setDate(squad.getMeetingTime());
        squadResponse.setLocation(squad.getMeetingPoint());
        squadResponse.setPlayers(squad.getPlayers().stream().map(PlayerUtil::mapPlayerToSelectDTO).toList());

        return squadResponse;
    }
}
