package com.web.meuequipo.core.image.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "meuequipo.uploads")
public class ImageProperties {
    @NotBlank
    private String path;
}
