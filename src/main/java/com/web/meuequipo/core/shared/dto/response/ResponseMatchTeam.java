package com.web.meuequipo.core.shared.dto.response;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

@Data
public class ResponseMatchTeam {
    private Long id;
    private ImageViewDTO logo;
    private String name;
    private Boolean isOurTeam;
}
