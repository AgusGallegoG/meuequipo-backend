package com.web.meuequipo.core.user.service;


import com.web.meuequipo.core.user.User;
import com.web.meuequipo.core.user.data.UserRepository;
import com.web.meuequipo.core.user.dto.request.UserSaveRequest;
import com.web.meuequipo.core.user.dto.response.UserResponse;
import com.web.meuequipo.core.user.exception.UserException;
import com.web.meuequipo.core.user.util.UserUtil;
import com.web.meuequipo.core.util.UtilPassword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
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
    public void saveUser(UserSaveRequest request) {
        if (Objects.isNull(request.getId())) {
            this.createUser(request);
        } else {
            this.updateUser(request);
        }
    }

    private void createUser(UserSaveRequest request) {
        String password = UtilPassword.generateSecurePass();

        User user = new User();

        user.setActive(request.getActive());
        user.setName(request.getName());
        user.setSurnames(request.getSurnames());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(password));

        //TODO :> Send mail to user with info

        userRepository.save(user);
    }

    private void updateUser(UserSaveRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new UserException("Non se atopa o usuario en BD"));

        user.setActive(request.getActive());
        user.setName(request.getName());
        user.setSurnames(request.getSurnames());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }
}
