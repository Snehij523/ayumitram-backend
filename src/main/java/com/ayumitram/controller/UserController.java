package com.ayumitram.controller;

import com.ayumitram.dto.response.ApiResponse;
import com.ayumitram.entity.User;
import com.ayumitram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<User>> getProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserProfile(email);
        // Do not expose password
        user.setPassword(null);
        return ResponseEntity.ok(new ApiResponse<>(true, "User profile retrieved", user));
    }
}