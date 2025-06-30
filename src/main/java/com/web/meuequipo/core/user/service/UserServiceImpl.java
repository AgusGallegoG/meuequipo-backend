package com.web.meuequipo.core.user.service;


import com.web.meuequipo.core.user.User;
import com.web.meuequipo.core.user.data.UserRepository;
import com.web.meuequipo.core.user.dto.request.UserSaveRequest;
import com.web.meuequipo.core.user.dto.response.UserResponse;
import com.web.meuequipo.core.user.exception.UserException;
import com.web.meuequipo.core.user.util.UserUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final static String DEFAULT_PASSWORD = "123password";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findByIdAndIsActiveIsTrue(userId)
                .orElseThrow(() -> new UserException("Non se atopa o usuario en base de datos"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserUtil::mapUserToUserResponse);
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserSaveRequest request) {
        User saved;
        if (Objects.isNull(request.getId())) {
            saved = this.createUser(request);
        } else {
            saved = this.updateUser(request);
        }

        return UserUtil.mapUserToUserResponse(saved);
    }

    private User createUser(UserSaveRequest request) {
        User user = new User();

        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));

        return saveUserEntity(request, user);
    }

    private User updateUser(UserSaveRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new UserException("Non se atopa o usuario en BD"));

        return saveUserEntity(request, user);
    }

    private User saveUserEntity(UserSaveRequest request, User user) {
        user.setActive(request.getActive());
        user.setName(request.getName());
        user.setSurnames(request.getSurnames());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }
}
