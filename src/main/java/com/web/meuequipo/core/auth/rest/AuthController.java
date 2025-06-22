package com.web.meuequipo.core.auth.rest;

import com.web.meuequipo.core.auth.dto.request.ChangePassRequest;
import com.web.meuequipo.core.auth.dto.request.LoginRequest;
import com.web.meuequipo.core.auth.dto.response.LoginResponse;
import com.web.meuequipo.core.auth.security.CustomUserDetails;
import com.web.meuequipo.core.auth.service.AuthService;
import com.web.meuequipo.core.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/changepass")
    public void changePassword(@RequestBody ChangePassRequest changePassRequest,
                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.userService.changePassword(userDetails.getId(), changePassRequest.getOldPass(), changePassRequest.getNewPass());
    }
}
