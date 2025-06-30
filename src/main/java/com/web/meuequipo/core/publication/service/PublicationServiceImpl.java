package com.web.meuequipo.core.publication.service;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.dto.ImageViewDTO;
import com.web.meuequipo.core.publication.Publication;
import com.web.meuequipo.core.publication.data.PublicationRepository;
import com.web.meuequipo.core.publication.dto.request.PublicationSaveRequest;
import com.web.meuequipo.core.publication.dto.response.PublicationResponse;
import com.web.meuequipo.core.publication.exception.PublicationException;
import com.web.meuequipo.core.publication.util.PublicationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    private final ImageRepository imageRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository, ImageRepository imageRepository) {
        this.publicationRepository = publicationRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public Page<PublicationResponse> getAllPublications(Pageable pageable) {
        Page<Publication> response = this.publicationRepository.findAllOrderByCreatedDateDesc(pageable);

        return response.map(PublicationUtil::mapPublicationToDTO);
    }

    @Override
    public Page<PublicationResponse> getAllPublicationsAdmin(Pageable pageable) {
        Page<Publication> response = this.publicationRepository.findAllPage(pageable);

        return response.map(PublicationUtil::mapPublicationToDTO);
    }

    @Override
    public List<PublicationResponse> getLastPublications() {
        List<Publication> response = this.publicationRepository.findNewPublications(PageRequest.of(0, 5));
        return response.stream().map(PublicationUtil::mapPublicationToDTO).toList();
    }

    @Override
    @Transactional
    public PublicationResponse savePublication(PublicationSaveRequest request) {
        Publication saved;
        if (request.getId() != null) {
            saved = this.updatePublication(request);
        } else {
            saved = this.createPublication(request);
        }
        return PublicationUtil.mapPublicationToDTO(saved);
    }

    private Publication createPublication(PublicationSaveRequest request) {
        Publication publication = new Publication();
        this.mapPublicationEntity(request, publication);
        return publicationRepository.save(publication);
    }

    private Publication updatePublication(PublicationSaveRequest request) {
        Publication publication = publicationRepository.findById(request.getId())
                .orElseThrow(() -> new PublicationException("Publication non atopada con id: " + request.getId()));
        this.mapPublicationEntity(request, publication);
        return publicationRepository.save(publication);
    }

    private void mapPublicationEntity(PublicationSaveRequest request, Publication publication) {
        publication.setBody(request.getBody());
        publication.setTitle(request.getTitle());
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            updateImages(publication, request.getImages().
                    stream().map(ImageViewDTO::getId).toList());
        }
    }

    private void updateImages(Publication publication, List<Long> imageIds) {

        List<Image> newImages = imageRepository.findAllById(imageIds);

        if (newImages.isEmpty() || newImages.size() != imageIds.size()) {
            throw new PublicationException("O numero de imaxes asociadas non corresponde co numero de imaxes obtidas de base de datos");
        }

        List<Image> actualImages = new ArrayList<>(publication.getImages());

        for (Image image : actualImages) {
            publication.removeImage(image);
        }

        for (Image image : newImages) {
            publication.addImage(image);
        }

    }
}
