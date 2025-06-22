package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

@Getter
public enum SexPlayers {
    MALE(1, "Home"),
    FEMALE(2, "Muller");

    private final int id;
    private final String label;

    SexPlayers(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static SexPlayers fromId(int id) {
        for (SexPlayers p : SexPlayers.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new IllegalArgumentException("No PlayerSex with id " + id);
    }

}
