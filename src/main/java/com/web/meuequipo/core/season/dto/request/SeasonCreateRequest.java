package com.web.meuequipo.core.season.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SeasonCreateRequest {
    Boolean active;
    LocalDate startDate;
    LocalDate endDate;
}
