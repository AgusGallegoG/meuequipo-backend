package com.web.meuequipo.core.rival.rest;

import com.web.meuequipo.core.rival.dto.request.RequestSaveRival;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalDetails;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalItem;
import com.web.meuequipo.core.rival.service.RivalService;
import com.web.meuequipo.core.shared.dto.response.ResponseMatchTeam;
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
    ResponseEntity<ResponseRivalDetails> getRivalDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rivalService.getRivalDetails(id));
    }

    @GetMapping("/matchTeam")
    List<ResponseMatchTeam> getRivalsAsMatchTeam(@RequestParam(name = "categoryId") Long categoryId) {
        return rivalService.getRivalsByCategory(categoryId);
    }

    @GetMapping
    Page<ResponseRivalItem> getRivals(Pageable pageable) {
        return rivalService.getRivalsTable(pageable);
    }

    @PostMapping
    ResponseEntity<ResponseRivalItem> saveRival(@RequestBody RequestSaveRival requestSaveRival) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rivalService.saveRivalItem(requestSaveRival));
    }
}
