package com.web.meuequipo.core.rival.dto.response;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

@Data
public class ResponseRivalItem {
    private Long id;
    private String name;
    private String responsible;
    private String tlf;
    private ImageViewDTO logo;
}
