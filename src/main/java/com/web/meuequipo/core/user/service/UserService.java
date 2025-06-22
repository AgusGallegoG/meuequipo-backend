package com.web.meuequipo.core.user.service;

public interface UserService {
    void changePassword(Long userId, String oldPassword, String newPassword);
}
