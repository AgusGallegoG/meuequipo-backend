package com.web.meuequipo.core.game.util;

import com.web.meuequipo.core.game.Game;
import com.web.meuequipo.core.game.dto.response.GameResponse;
import com.web.meuequipo.core.rival.util.RivalUtil;
import com.web.meuequipo.core.squad.util.SquadUtil;
import com.web.meuequipo.core.team.util.TeamUtil;

public class GameUtil {

    public static GameResponse mapGameToGameResponseWithoutSquad(Game game) {
        GameResponse gameResponse = new GameResponse();

        mapCommon(game, gameResponse); //ocultar squad

        return gameResponse;
    }

    public static GameResponse mapGameToGameResponseWithSquad(Game game) {
        GameResponse gameResponse = new GameResponse();

        mapCommon(game, gameResponse);

        if (game.getSquad() != null) {
            gameResponse.setSquad(SquadUtil.createSquadResponse(game.getSquad()));
        }

        return gameResponse;
    }

    private static void mapCommon(Game game, GameResponse gameResponse) {
        gameResponse.setId(game.getId());
        mapTeamsByIsLocal(game, gameResponse);
        gameResponse.setLocalPoints(game.getLocalPoints());
        gameResponse.setVisitorPoints(game.getVisitorPoints());
        gameResponse.setCategory(game.getCategory().getId());
        gameResponse.setGameDate(game.getGameDate());
        gameResponse.setLocation(game.getLocation());
        gameResponse.setState(game.getGameState());
    }

    private static void mapTeamsByIsLocal(Game game, GameResponse gameResponse) {
        if (game.getIsLocal()) {
            gameResponse.setLocalTeam(TeamUtil.mapTeamToResponseGameTeamResponse(game.getTeam()));
            gameResponse.setVisitorTeam(RivalUtil.mapRivalToGameTeamResponse(game.getRival()));
        } else {
            gameResponse.setLocalTeam(RivalUtil.mapRivalToGameTeamResponse(game.getRival()));
            gameResponse.setVisitorTeam(TeamUtil.mapTeamToResponseGameTeamResponse(game.getTeam()));
        }
    }
}
