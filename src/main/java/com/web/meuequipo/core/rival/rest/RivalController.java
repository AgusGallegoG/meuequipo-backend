package com.web.meuequipo.core.rival.rest;

import com.web.meuequipo.core.rival.dto.request.RivalSaveRequest;
import com.web.meuequipo.core.rival.dto.response.RivalDetailsResponse;
import com.web.meuequipo.core.rival.dto.response.RivalItemResponse;
import com.web.meuequipo.core.rival.service.RivalService;
import com.web.meuequipo.core.shared.dto.response.GameTeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rivals")
@PreAuthorize("isAuthenticated()")
public class RivalController {

    private final RivalService rivalService;

    public RivalController(RivalService rivalService) {
        this.rivalService = rivalService;
    }

    @GetMapping("/{id}")
    ResponseEntity<RivalDetailsResponse> getRivalDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rivalService.getRivalDetails(id));
    }

    @GetMapping("/match-team")
    List<GameTeamDTO> getRivalsAsGameTeam(@RequestParam(name = "categoryId") Long categoryId) {
        return rivalService.getRivalsByCategory(categoryId);
    }

    @GetMapping()
    Page<RivalItemResponse> getRivals(Pageable pageable) {
        return rivalService.getRivalsTable(pageable);
    }

    @PostMapping()
    ResponseEntity<RivalItemResponse> saveRival(@RequestBody RivalSaveRequest rivalSaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rivalService.saveRivalItem(rivalSaveRequest));
    }
}
