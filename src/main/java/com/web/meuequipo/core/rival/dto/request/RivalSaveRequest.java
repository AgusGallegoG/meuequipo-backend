package com.web.meuequipo.core.rival.dto.request;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

import java.util.List;

@Data
public class RivalSaveRequest {
    private Long id;
    private String name;
    private String responsible;
    private String tlf;
    private String email;
    private ImageViewDTO logo;
    private List<Long> categories;
}
