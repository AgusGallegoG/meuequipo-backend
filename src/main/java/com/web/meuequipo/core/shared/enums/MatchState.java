package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum MatchState {
    PRIVATE(1L, "Privado"),
    PROGRAMMED(2L, "Programado"),
    FINALIZED(3L, "Finalizado"),
    CANCELED(4L, "Cancelado");


    private final Long id;
    private final String label;

    MatchState(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public static MatchState fromId(Long id) {
        for (MatchState matchState : MatchState.values()) {
            if (Objects.equals(matchState.getId(), id)) {
                return matchState;
            }
        }
        throw new IllegalArgumentException("No MatchState with id " + id);
    }

}
