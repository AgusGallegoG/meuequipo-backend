package com.web.meuequipo.core.signin.dto.response;

import lombok.Data;

@Data
public class SigninResponse {
    private Long id;
    private String parentName;
    private String parentSurnames;
    private String mail;
    private String phone;
    private Long state;
    private PlayerResponse player;
}
