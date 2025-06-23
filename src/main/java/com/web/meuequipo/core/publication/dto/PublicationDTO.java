package com.web.meuequipo.core.publication.dto;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

import java.util.List;

@Data
public class PublicationDTO {
    Long id;
    String title;
    String body;
    String creationDate;
    List<ImageViewDTO> images;
}
