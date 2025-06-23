package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum SexPlayers {
    MALE(1L, "Home"),
    FEMALE(2L, "Muller");

    private final Long id;
    private final String label;

    SexPlayers(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public static SexPlayers fromId(Long id) {
        for (SexPlayers p : SexPlayers.values()) {
            if (Objects.equals(p.getId(), id)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No PlayerSex with id " + id);
    }

}
