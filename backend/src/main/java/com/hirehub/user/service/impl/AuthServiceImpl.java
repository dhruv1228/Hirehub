package com.hirehub.user.service.impl;

import com.hirehub.common.email.EmailService;
import com.hirehub.common.exception.EmailAlreadyExistsException;
import com.hirehub.common.security.jwt.JwtService;
import com.hirehub.user.dto.AuthResponse;
import com.hirehub.user.dto.LoginRequest;
import com.hirehub.user.dto.RegisterRequest;
import com.hirehub.user.dto.UserResponse;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import com.hirehub.user.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.hirehub.common.email.EmailTemplates;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .phone(request.getPhone())
        .role(request.getRole())
        .enabled(true)
        .accountNonLocked(true)
        .build();

        User savedUser = userRepository.save(user);

        try {
                emailService.sendEmail(
                savedUser.getEmail(),
                "Welcome to HireHub",
                EmailTemplates.welcome(savedUser.getFirstName())
        );
        } catch (Exception ex) {
        ex.printStackTrace(); // Later replace with proper logging
        }

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .build();
    }
   @Override
        public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user.getEmail());

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .user(userResponse)
                .build();
        }
}