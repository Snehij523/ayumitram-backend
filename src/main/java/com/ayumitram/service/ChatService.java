package com.ayumitram.service;

import com.ayumitram.dto.request.ChatRequest;
import com.ayumitram.dto.response.ChatResponse;
import com.ayumitram.entity.ChatHistory;

import java.util.List;

public interface ChatService {
    ChatResponse askQuestion(ChatRequest request, String userEmail);
    List<ChatHistory> getChatHistory(String userEmail);
}