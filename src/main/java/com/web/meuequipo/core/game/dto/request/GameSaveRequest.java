package com.web.meuequipo.core.game.dto.request;

import com.web.meuequipo.core.shared.dto.request.GameTeamRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameSaveRequest {
    private Long id;
    private GameTeamRequest localTeam;
    private GameTeamRequest visitorTeam;
    private Integer localPoints;
    private Integer visitorPoints;
    private Long category;
    private LocalDateTime gameDate;
    private String location;
    private Long state;
}
