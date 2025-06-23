package com.web.meuequipo.core.sponsor.dto.request;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

@Data
public class SponsorSaveRequest {
    private Long id;
    private String name;
    private String url;
    private ImageViewDTO logo;
}
