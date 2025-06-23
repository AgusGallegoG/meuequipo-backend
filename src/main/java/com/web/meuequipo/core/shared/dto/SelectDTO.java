package com.web.meuequipo.core.shared.dto;

import lombok.Data;

@Data
public class SelectDTO {
    private Long id;
    private String name;

    public SelectDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
