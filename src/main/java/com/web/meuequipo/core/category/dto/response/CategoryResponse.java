package com.web.meuequipo.core.category.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private LocalDate yearInit;
    private LocalDate yearEnd;
    private boolean active;
}
