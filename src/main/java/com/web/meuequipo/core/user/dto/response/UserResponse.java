package com.web.meuequipo.core.user.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String surnames;
    private String email;
    private Boolean active;
}
