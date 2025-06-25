package com.web.meuequipo.core.publication.util;

import com.web.meuequipo.core.image.util.ImageUtil;
import com.web.meuequipo.core.publication.Publication;
import com.web.meuequipo.core.publication.dto.response.PublicationResponse;

public class PublicationUtil {

    public static PublicationResponse mapPublicationToDTO(final Publication publication) {
        final PublicationResponse response = new PublicationResponse();

        response.setId(publication.getId());
        response.setBody(publication.getBody());
        response.setTitle(publication.getTitle());

        response.setCreationDate(publication.getCreatedDate());

        if (!publication.getImages().isEmpty()) {
            response.setImages(publication.getImages().stream().map(ImageUtil::getImageViewDTO).toList());
        }

        return response;
    }

}
