package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

@Getter
public enum Sex {
    MALE(1, "Masculino"),
    FEMALE(2, "Feminino"),
    MIXED(3, "MIxto");

    private final int id;
    private final String label;

    Sex(int id, String label) {
        this.id = id;
        this.label = label;
    }


    public static Sex fromId(int id) {
        for (Sex s : Sex.values()) {
            if (s.id == id) {
                return s;
            }
        }
        throw new IllegalArgumentException("No Sex with id: " + id);
    }
}
