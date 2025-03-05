package ru.previzorz.registration.service;

import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.previzorz.registration.dto.UserDTO;
import ru.previzorz.registration.entity.User;
import ru.previzorz.registration.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(UserDTO userDTO) {
        String normalizedEmail = userDTO.getEmail().toLowerCase();

        userRepository.findByEmail(normalizedEmail)
                .ifPresent(user -> {
                    throw new EntityExistsException("User with this email already exists.");
                });

        User user = new User();
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }
}
