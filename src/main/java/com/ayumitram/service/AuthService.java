package com.ayumitram.service;

import com.ayumitram.dto.request.LoginRequest;
import com.ayumitram.dto.request.RegisterRequest;
import com.ayumitram.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}