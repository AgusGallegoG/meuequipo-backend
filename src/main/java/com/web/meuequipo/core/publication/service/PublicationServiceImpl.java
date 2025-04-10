package com.web.meuequipo.core.publication.service;

import com.web.meuequipo.core.publication.Publication;
import com.web.meuequipo.core.publication.data.PublicationRepository;
import com.web.meuequipo.core.publication.dto.PublicationDTO;
import com.web.meuequipo.core.publication.util.UtilPublication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }


    @Override
    public Page<PublicationDTO> getAllPublications(Pageable pageable) {
        Page<Publication> response = this.publicationRepository.findAll(pageable);

        return response.map(UtilPublication::mapPublicationToDTO);
    }
}
