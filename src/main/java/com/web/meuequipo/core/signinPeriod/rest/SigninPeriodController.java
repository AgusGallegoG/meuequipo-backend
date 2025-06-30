package com.web.meuequipo.core.signinPeriod.rest;

import com.web.meuequipo.core.signinPeriod.dto.request.SigninPeriodUpdateRequest;
import com.web.meuequipo.core.signinPeriod.dto.response.SigninPeriodInfoResponse;
import com.web.meuequipo.core.signinPeriod.service.SigninPeriodService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/signin")
public class SigninPeriodController {
    private static final String FILENAME = "Inscripcion_CBXVerin.pdf";

    private final SigninPeriodService signinPeriodService;

    public SigninPeriodController(SigninPeriodService signinPeriodService) {
        this.signinPeriodService = signinPeriodService;
    }

    @GetMapping("/period-active")
    public ResponseEntity<Boolean> isPeriodActive() {
        return ResponseEntity.ok(signinPeriodService.checkActive());
    }

    @PatchMapping("/period")
    public ResponseEntity<SigninPeriodInfoResponse> updatePeriod(@RequestBody SigninPeriodUpdateRequest request) {
        return ResponseEntity.ok(signinPeriodService.updatePeriod(request));
    }

    @PostMapping("/form")
    public ResponseEntity<SigninPeriodInfoResponse> updateForm(MultipartFile file) {
        return ResponseEntity.ok(signinPeriodService.updateForm(file));
    }

    @GetMapping("/info")
    public ResponseEntity<SigninPeriodInfoResponse> getInfo() {
        return ResponseEntity.ok(signinPeriodService.getInfo());
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download() {
        Resource resource = signinPeriodService.downloadForm();

        String filename = FILENAME;

        if (resource.getFilename() != null && resource.getFilename().endsWith(".pdf")) {
            filename = resource.getFilename();
        }

        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8).replace("+", "%20");//evitamos problemas con caracteres especiales

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(resource);

    }
}
