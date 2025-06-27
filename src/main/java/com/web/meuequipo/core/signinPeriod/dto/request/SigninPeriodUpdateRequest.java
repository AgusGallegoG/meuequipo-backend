package com.web.meuequipo.core.signinPeriod.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SigninPeriodUpdateRequest {
    private Long id;
    private LocalDate dateInit;
    private LocalDate dateEnd;
}
