package com.web.meuequipo.core.user.service;

import com.web.meuequipo.core.user.dto.request.UserSaveRequest;
import com.web.meuequipo.core.user.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void changePassword(Long userId, String oldPassword, String newPassword);

    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse saveUser(UserSaveRequest request);
}
