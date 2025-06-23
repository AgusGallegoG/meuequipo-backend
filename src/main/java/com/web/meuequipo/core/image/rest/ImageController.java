package com.web.meuequipo.core.image.rest;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.image.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public ImageViewDTO saveImage(MultipartFile file) {
        return imageService.saveImage(file);
    }

    @GetMapping("/serve/{relativePath:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable(value = "relativePath") String relativePath) {
        try {
            Resource resource = imageService.serveImage(relativePath);
            String contentType = Files.probeContentType(resource.getFile().toPath());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream")).body(resource);
        } catch (ImageException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
