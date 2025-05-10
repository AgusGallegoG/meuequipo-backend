package com.web.meuequipo.core.publication.rest;

import com.web.meuequipo.core.publication.dto.PublicationDTO;
import com.web.meuequipo.core.publication.service.PublicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class PublicationRestController {

    private final PublicationService publicationService;

    public PublicationRestController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping(name = "/all")
    public Page<PublicationDTO> getAllBlogPublications(@RequestParam Pageable pageable) {
        return this.publicationService.getAllPublications(pageable);
    }
}
