package com.web.meuequipo.core.squad.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SquadCreateRequest {
    private Long team;
    private Long game;
    private List<Long> players;
    private String locationMeeting;
    private LocalDateTime dateMeeting;
}
