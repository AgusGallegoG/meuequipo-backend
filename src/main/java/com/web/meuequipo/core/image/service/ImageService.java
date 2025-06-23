package com.web.meuequipo.core.image.service;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageViewDTO saveImage(MultipartFile file);

    Resource serveImage(String relativePath);

    ImageViewDTO getImageViewDTOById(Long id);
}
