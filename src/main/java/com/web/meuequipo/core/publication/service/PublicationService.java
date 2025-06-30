package com.web.meuequipo.core.publication.service;

import com.web.meuequipo.core.publication.dto.request.PublicationSaveRequest;
import com.web.meuequipo.core.publication.dto.response.PublicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationService {

    Page<PublicationResponse> getAllPublications(Pageable pageable);

    Page<PublicationResponse> getAllPublicationsAdmin(Pageable pageable);

    List<PublicationResponse> getLastPublications();

    PublicationResponse savePublication(PublicationSaveRequest request);
}
