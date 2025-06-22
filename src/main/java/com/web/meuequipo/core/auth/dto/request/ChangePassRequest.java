package com.web.meuequipo.core.auth.dto.request;

import lombok.Data;

@Data
public class ChangePassRequest {
    private String oldPass;
    private String newPass;
}
