package com.web.meuequipo.core.image.util;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.dto.ImageViewDTO;

public class ImageUtil {

    public static ImageViewDTO getImageViewDTO(Image image) {
        ImageViewDTO imageDTO = new ImageViewDTO();

        imageDTO.setId(image.getId());
        imageDTO.setName(image.getOriginalName());

        return imageDTO;
    }
}
