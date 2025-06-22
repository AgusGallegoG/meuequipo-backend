package com.web.meuequipo.core.user.service;


import com.web.meuequipo.core.user.User;
import com.web.meuequipo.core.user.data.UserRepository;
import com.web.meuequipo.core.user.exception.UserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = userRepository.findByIdAndActiveIsTrue(userId)
                .orElseThrow(() -> new UserException("Non se atopa o usuario en base de datos"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
