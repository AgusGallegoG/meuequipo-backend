package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Sex {
    MALE(1L, "Masculino"),
    FEMALE(2L, "Feminino"),
    MIXED(3L, "Mixto");

    private final Long id;
    private final String label;

    Sex(Long id, String label) {
        this.id = id;
        this.label = label;
    }


    public static Sex fromId(Long id) {
        for (Sex s : Sex.values()) {
            if (Objects.equals(s.id, id)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No Sex with id: " + id);
    }
}
