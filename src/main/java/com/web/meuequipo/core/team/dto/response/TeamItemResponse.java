package com.web.meuequipo.core.team.dto.response;

import lombok.Data;

@Data
public class TeamItemResponse {
    private Long id;
    private String name;
    private Long category;
    private Long sex;
    private String trainer;
    private Integer playerCount;
}
