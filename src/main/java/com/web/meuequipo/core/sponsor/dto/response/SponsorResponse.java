package com.web.meuequipo.core.sponsor.dto.response;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

@Data
public class SponsorResponse {
    private Long id;
    private String name;
    private String url;
    private ImageViewDTO logo;
}
