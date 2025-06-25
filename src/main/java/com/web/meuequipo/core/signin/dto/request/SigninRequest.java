package com.web.meuequipo.core.signin.dto.request;

import lombok.Data;

@Data
public class SigninRequest {
    private Long id;
    private String parentName;
    private String parentSurnames;
    private String mail;
    private String phone;
    private Long state; //null on create -> Auto set
    private PlayerRequest player;
}
