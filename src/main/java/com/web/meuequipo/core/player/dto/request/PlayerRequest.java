package com.web.meuequipo.core.player.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerRequest {
    private Long id;
    private String name;
    private String surnames;
    private LocalDate birthDate;
    private Long sex;
    private Long category;
}
