package com.web.meuequipo.core.signinPeriod.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "meuequipo.signin")
public class SigninPeriodProperties {
    @NotBlank
    private String path;
}
