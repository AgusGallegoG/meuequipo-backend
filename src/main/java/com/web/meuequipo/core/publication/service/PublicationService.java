package com.web.meuequipo.core.publication.service;

import com.web.meuequipo.core.publication.dto.PublicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicationService {

    Page<PublicationDTO> getAllPublications(Pageable pageable);
}
