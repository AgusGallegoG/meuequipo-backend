package com.web.meuequipo.core.image.rest;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.image.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/images")
public class ImageController {


    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public ImageViewDTO saveImage(MultipartFile file) {
        return imageService.saveImage(file);
    }

    @GetMapping("/serve/{id}")
    public ResponseEntity<Resource> serveImage(@PathVariable(value = "id") Long id) {
        try {
            Resource resource = imageService.serveImage(id);
            String contentType = Files.probeContentType(resource.getFile().toPath());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                    .body(resource);
        } catch (ImageException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
