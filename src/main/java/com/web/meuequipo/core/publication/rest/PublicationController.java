package com.web.meuequipo.core.publication.rest;

import com.web.meuequipo.core.publication.dto.request.PublicationSaveRequest;
import com.web.meuequipo.core.publication.dto.response.PublicationResponse;
import com.web.meuequipo.core.publication.service.PublicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("isAuthenticated()")
    public Page<PublicationResponse> getAllBlogPublications(Pageable pageable) {
        return publicationService.getAllPublications(pageable);
    }

    @GetMapping(value = "/public")
    public Page<PublicationResponse> getAllBlogPublicPublications(Pageable pageable) {
        return publicationService.getAllPublications(pageable);
    }

    @GetMapping(value = "/public/init")
    public List<PublicationResponse> getAllBlogPublicationsInit() {
        return publicationService.getLastPublications();
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PublicationResponse> savePublication(@RequestBody PublicationSaveRequest publicationSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.savePublication(publicationSaveRequest));
    }
}
