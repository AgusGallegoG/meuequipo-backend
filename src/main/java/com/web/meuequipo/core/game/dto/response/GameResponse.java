package com.web.meuequipo.core.game.dto.response;

import com.web.meuequipo.core.shared.dto.response.GameTeamResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameResponse {
    private Long id;
    private GameTeamResponse localTeam;
    private GameTeamResponse visitorTeam;
    private Integer localPoints;
    private Integer visitorPoints;
    private Long category;
    private LocalDateTime gameDate;
    private String location;
    private Long state;
    //TODO add squad
}
