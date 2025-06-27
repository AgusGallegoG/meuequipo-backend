package com.web.meuequipo.core.signinPeriod.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SigninPeriodInfoResponse {
    private Long id;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Integer downloads;
    private Boolean hasForm;
    private Boolean isActive;
}
