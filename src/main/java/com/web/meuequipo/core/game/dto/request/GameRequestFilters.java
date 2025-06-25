package com.web.meuequipo.core.game.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GameRequestFilters {
    private LocalDate from;
    private LocalDate to;
    private Long team;
    private Boolean isSquad;
}
