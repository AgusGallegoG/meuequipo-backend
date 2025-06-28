package com.web.meuequipo.core.season.rest;

import com.web.meuequipo.core.season.dto.request.SeasonCreateRequest;
import com.web.meuequipo.core.season.dto.response.SeasonResponse;
import com.web.meuequipo.core.season.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/all")
    public List<SeasonResponse> getAllSeasons() {
        return seasonService.getAllSeasons();
    }


    @PatchMapping("/{id}/activate")
    public ResponseEntity<SeasonResponse> activateSeason(@PathVariable Long id) {
        return ResponseEntity.ok(seasonService.activateSeason(id));
    }


    @PostMapping()
    public ResponseEntity<SeasonResponse> createSeason(@RequestBody SeasonCreateRequest seasonCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seasonService.createSeason(seasonCreateRequest));
    }
}
