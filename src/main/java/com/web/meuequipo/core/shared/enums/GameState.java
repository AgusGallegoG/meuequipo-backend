package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum GameState {
    PRIVATE(1L, "Privado"),
    PROGRAMMED(2L, "Programado"),
    FINALIZED(3L, "Finalizado"),
    CANCELED(4L, "Cancelado");


    private final Long id;
    private final String label;

    GameState(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public static GameState fromId(Long id) {
        for (GameState gameState : GameState.values()) {
            if (Objects.equals(gameState.getId(), id)) {
                return gameState;
            }
        }
        throw new IllegalArgumentException("No GameState with id " + id);
    }

}
