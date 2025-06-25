package com.web.meuequipo.core.publication.dto.request;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

import java.util.List;

@Data
public class PublicationSaveRequest {
    private Long id;
    private String title;
    private String body;
    private List<ImageViewDTO> images;
}
