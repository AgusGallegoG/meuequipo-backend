package com.web.meuequipo.core.signin.dto.response;

import lombok.Data;

@Data
public class SigninItemResponse {
    private Long id;
    private String playerCompleteName;
    private Long category;
    private Long state;
    private Long sex;

    private String parentCompleteName;
    private String email;
    private String phone;
}
