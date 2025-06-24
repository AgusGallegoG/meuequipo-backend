package com.web.meuequipo.core.publication.util;

import com.web.meuequipo.core.publication.Publication;
import com.web.meuequipo.core.publication.dto.PublicationDTO;

public class UtilPublication {

    public static PublicationDTO mapPublicationToDTO(final Publication publication) {
        final PublicationDTO response = new PublicationDTO();

        response.setId(publication.getId());
        response.setBody(publication.getBody());
        response.setTitle(publication.getTitle());

        return response;
    }

}
