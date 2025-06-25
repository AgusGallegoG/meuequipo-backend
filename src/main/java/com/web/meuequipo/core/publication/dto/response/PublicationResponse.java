package com.web.meuequipo.core.publication.dto.response;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicationResponse {
    Long id;
    String title;
    String body;
    LocalDateTime creationDate;
    List<ImageViewDTO> images;
}
