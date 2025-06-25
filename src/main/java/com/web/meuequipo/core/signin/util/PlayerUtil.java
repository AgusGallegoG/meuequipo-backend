package com.web.meuequipo.core.signin.util;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.signin.Player;
import com.web.meuequipo.core.signin.dto.request.SigninRequest;
import com.web.meuequipo.core.signin.dto.response.PlayerResponse;
import com.web.meuequipo.core.signin.dto.response.SigninItemResponse;
import com.web.meuequipo.core.signin.dto.response.SigninResponse;

public class PlayerUtil {

    public static void mapPublicSigninSingleFieldsToPlayer(SigninRequest signinRequest, Player player) {
        player.setParentName(signinRequest.getParentName());
        player.setParentSurnames(signinRequest.getParentSurnames());
        player.setMail(signinRequest.getMail());
        player.setPhone(signinRequest.getPhone());
        player.setName(signinRequest.getPlayer().getName());
        player.setSurnames(signinRequest.getPlayer().getSurnames());
        player.setBirthDate(signinRequest.getPlayer().getBirthDate());
        player.setPlayerSex(signinRequest.getPlayer().getSex());
    }

    public static SigninResponse mapPlayerToSigninResponse(Player player) {
        SigninResponse signinResponse = new SigninResponse();

        signinResponse.setId(player.getId());
        signinResponse.setParentName(player.getParentName());
        signinResponse.setParentSurnames(player.getParentSurnames());
        signinResponse.setMail(player.getMail());
        signinResponse.setPhone(player.getPhone());
        signinResponse.setState(player.getSigninState());
        signinResponse.setPlayer(mapPlayerToResponsePlayer(player));

        return signinResponse;
    }

    private static PlayerResponse mapPlayerToResponsePlayer(Player player) {
        PlayerResponse playerResponse = new PlayerResponse();

        playerResponse.setId(player.getId());
        playerResponse.setName(player.getName());
        playerResponse.setSurnames(player.getSurnames());
        playerResponse.setBirthDate(player.getBirthDate());
        playerResponse.setSex(player.getPlayerSex());
        playerResponse.setCategory(player.getCategory().getId());

        return playerResponse;
    }

    public static SigninItemResponse mapPlayerToSigninItemResponse(Player player) {
        SigninItemResponse signinItemResponse = new SigninItemResponse();

        signinItemResponse.setId(player.getId());
        signinItemResponse.setPlayerCompleteName(player.getPlayerCompleteName());
        signinItemResponse.setCategory(player.getCategory().getId());
        signinItemResponse.setState(player.getSigninState());
        signinItemResponse.setSex(player.getPlayerSex());
        signinItemResponse.setParentCompleteName(player.getParentCompleteName());
        signinItemResponse.setEmail(player.getMail());
        signinItemResponse.setPhone(player.getPhone());

        return signinItemResponse;
    }

    public static SelectDTO mapPlayerToSelectDTO(Player player) {
        return new SelectDTO(player.getId(), player.getPlayerCompleteName());
    }
}
