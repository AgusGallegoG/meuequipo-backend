package com.web.meuequipo.core.category.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CategorySaveRequest {
    private Long id;
    private String name;
    private LocalDate yearInit;
    private LocalDate yearEnd;
    private boolean active;
}
