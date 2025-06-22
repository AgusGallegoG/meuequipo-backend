package com.web.meuequipo.core.season.rest;

import com.web.meuequipo.core.season.dto.request.SeasonCreateRequest;
import com.web.meuequipo.core.season.dto.response.SeasonResponse;
import com.web.meuequipo.core.season.service.SeasonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/season")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/active")
    public SeasonResponse getActiveSeason() {
        return seasonService.getActiveSeason();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public List<SeasonResponse> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}/activate")
    public List<SeasonResponse> activateSeason(@PathVariable Long id) {
        return seasonService.activateSeason(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    public List<SeasonResponse> createSeason(@RequestBody SeasonCreateRequest seasonCreateRequest) {
        return seasonService.createSeason(seasonCreateRequest);
    }
}
