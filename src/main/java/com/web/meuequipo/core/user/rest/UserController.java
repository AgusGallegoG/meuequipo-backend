package com.web.meuequipo.core.user.rest;

import com.web.meuequipo.core.user.dto.request.UserSaveRequest;
import com.web.meuequipo.core.user.dto.response.UserResponse;
import com.web.meuequipo.core.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Page<UserResponse> getUsersPage(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserSaveRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

}
