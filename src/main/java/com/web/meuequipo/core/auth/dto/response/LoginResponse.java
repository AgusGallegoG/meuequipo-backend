package com.web.meuequipo.core.auth.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String token;
    private String completeName;
    private String email;
    private List<String> authorities;

    public LoginResponse(String token, String username, String email, List<String> list) {
        this.token = token;
        this.completeName = username;
        this.email = email;
        this.authorities = list;
    }
}
