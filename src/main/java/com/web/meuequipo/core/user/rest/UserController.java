package com.web.meuequipo.core.user.rest;

import com.web.meuequipo.core.user.dto.request.UserSaveRequest;
import com.web.meuequipo.core.user.dto.response.UserResponse;
import com.web.meuequipo.core.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Page<UserResponse> getUsersPage(Pageable pageable) {
        return this.userService.getUsers(pageable);
    }

    @PostMapping("/")
    public Page<UserResponse> saveUser(@RequestBody UserSaveRequest request, @ModelAttribute Pageable pageable) {
        this.userService.saveUser(request);
        return this.userService.getUsers(pageable);
    }

}
