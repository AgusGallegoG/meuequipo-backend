package com.web.meuequipo.core.player.util;

import com.web.meuequipo.core.player.Player;
import com.web.meuequipo.core.player.dto.response.PlayerResponse;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;

public class PlayerUtil {


    public static PlayerResponse mapPlayerToResponsePlayer(Player player) {
        PlayerResponse playerResponse = new PlayerResponse();

        playerResponse.setId(player.getId());
        playerResponse.setName(player.getName());
        playerResponse.setSurnames(player.getSurnames());
        playerResponse.setBirthDate(player.getBirthDate());
        playerResponse.setSex(player.getPlayerSex());
        playerResponse.setCategory(player.getCategory().getId());

        return playerResponse;
    }



    public static SelectDTO mapPlayerToSelectDTO(Player player) {
        return new SelectDTO(player.getId(), player.getPlayerCompleteName());
    }
}
