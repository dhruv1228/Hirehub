package com.hirehub.user.service;

import com.hirehub.user.dto.*;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}