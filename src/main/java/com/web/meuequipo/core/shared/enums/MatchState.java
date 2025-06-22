package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

@Getter
public enum MatchState {
    PRIVATE(1, "Privado"),
    PROGRAMMED(2, "Programado"),
    FINALIZED(3, "Finalizado"),
    CANCELED(4, "Cancelado");


    private final int id;
    private final String label;

    MatchState(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static MatchState fromId(int id) {
        for (MatchState matchState : MatchState.values()) {
            if (matchState.getId() == id) {
                return matchState;
            }
        }
        throw new IllegalArgumentException("No MatchState with id " + id);
    }

}
