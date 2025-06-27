package com.web.meuequipo.core.player.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerResponse {
    private Long id;
    private String name;
    private String surnames;
    private LocalDate birthDate;
    private Long sex;
    private Long category;
}
