package com.web.meuequipo.core.sponsor.rest;

import com.web.meuequipo.core.sponsor.dto.request.SponsorSaveRequest;
import com.web.meuequipo.core.sponsor.dto.response.SponsorResponse;
import com.web.meuequipo.core.sponsor.service.SponsorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {

    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping("/footer")
    public List<SponsorResponse> getSponsorsFooter() {
        return sponsorService.getSponsorFooter();
    }

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public Page<SponsorResponse> getSponsors(Pageable pageable) {
        return sponsorService.getSponsorsTable(pageable);
    }

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SponsorResponse> saveSponsor(@RequestBody SponsorSaveRequest sponsorSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sponsorService.saveSponsor(sponsorSaveRequest));
    }
}
