package com.web.meuequipo.core.signin.rest;

import com.web.meuequipo.core.signin.dto.request.SigninRequest;
import com.web.meuequipo.core.signin.dto.response.SigninItemResponse;
import com.web.meuequipo.core.signin.dto.response.SigninResponse;
import com.web.meuequipo.core.signin.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signin")
public class SigninController {

    private final PlayerService playerService;

    public SigninController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    Page<SigninItemResponse> signinItemResponsePage(
            Pageable pageable,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "signinState", required = false) Long signinState) {
        return playerService.getSigninItemPage(pageable, categoryId, signinState);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<SigninResponse> getSigninById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.getSigninById(id));
    }

    @PostMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<SigninItemResponse> saveSigninAdmin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.saveAdminSignin(signinRequest));
    }

    @PostMapping("/public")
    ResponseEntity<Void> savePublicSignin(@RequestBody SigninRequest signinRequest) {
        playerService.savePublicSignin(signinRequest);
        return ResponseEntity.noContent().build();
    }

}
