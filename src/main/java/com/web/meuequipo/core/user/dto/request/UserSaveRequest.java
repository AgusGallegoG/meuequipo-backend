package com.web.meuequipo.core.user.dto.request;

import lombok.Data;

@Data
public class UserSaveRequest {
    private Long id;
    private String email;
    private String name;
    private String surnames;
    private Boolean active;
}
