package com.ayumitram.controller;

import com.ayumitram.dto.request.ChatRequest;
import com.ayumitram.dto.response.ApiResponse;
import com.ayumitram.dto.response.ChatResponse;
import com.ayumitram.entity.ChatHistory;
import com.ayumitram.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask")
    public ResponseEntity<ApiResponse<ChatResponse>> askQuestion(@RequestBody ChatRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        ChatResponse response = chatService.askQuestion(request, userEmail);
        return ResponseEntity.ok(new ApiResponse<>(true, "Response generated", response));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<ChatHistory>>> getChatHistory(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ChatHistory> history = chatService.getChatHistory(userEmail);
        return ResponseEntity.ok(new ApiResponse<>(true, "Chat history retrieved", history));
    }
}