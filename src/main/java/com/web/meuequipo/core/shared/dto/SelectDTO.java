package com.web.meuequipo.core.shared.dto;

import lombok.Data;

@Data
public class SelectDTO {
    private int id;
    private String name;

    public SelectDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
