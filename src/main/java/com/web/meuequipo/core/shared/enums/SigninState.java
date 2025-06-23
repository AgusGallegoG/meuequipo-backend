package com.web.meuequipo.core.shared.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum SigninState {
    INIT(1L, "Iniciada"),
    DELIVERED(2L, "Entregada, pendente de pago"),
    PAID(3L, "Pagada"),
    ENDED(4L, "Finalizada");


    private final Long id;
    private final String label;

    SigninState(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public static SigninState fromId(Long id) {
        for (SigninState s : SigninState.values()) {
            if (Objects.equals(s.getId(), id)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No SigninState with id " + id);
    }

}
