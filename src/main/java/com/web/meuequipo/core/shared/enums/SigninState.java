package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

@Getter
public enum SigninState {
    INIT(1, "Iniciada"),
    DELIVERED(2, "Entregada, pendente de pago"),
    PAID(3, "Pagada"),
    ENDED(4, "Finalizada");


    private final int id;
    private final String label;

    SigninState(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static SigninState fromId(int id) {
        for (SigninState s : SigninState.values()) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new IllegalArgumentException("No SigninState with id " + id);
    }

}
