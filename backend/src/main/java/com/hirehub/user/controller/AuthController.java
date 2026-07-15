package com.hirehub.user.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.common.utils.ResponseUtil;
import com.hirehub.user.dto.AuthResponse;
import com.hirehub.user.dto.LoginRequest;
import com.hirehub.user.dto.RegisterRequest;
import com.hirehub.user.dto.UserResponse;
import com.hirehub.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = authService.register(request);

        return ResponseUtil.success(
                response,
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseUtil.success(
                response,
                "Login successful"
        );
    }
}