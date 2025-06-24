package com.web.meuequipo.core.team.dto.response;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

@Data
public class TeamPublicResponse {
    private Long id;
    private String name;
    private Long category;
    private Long sex;
    private ImageViewDTO teamImage;
}
