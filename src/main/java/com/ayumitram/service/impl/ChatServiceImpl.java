package com.ayumitram.service.impl;

import com.ayumitram.dto.request.ChatRequest;
import com.ayumitram.dto.response.ChatResponse;
import com.ayumitram.entity.ChatHistory;
import com.ayumitram.entity.User;
import com.ayumitram.exception.ResourceNotFoundException;
import com.ayumitram.repository.ChatHistoryRepository;
import com.ayumitram.repository.UserRepository;
import com.ayumitram.service.ChatService;
import com.ayumitram.service.GeminiService;
import com.ayumitram.util.PromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final GeminiService geminiService;
    private final ChatHistoryRepository chatHistoryRepository;
    private final UserRepository userRepository;
    private final PromptBuilder promptBuilder;

    public ChatServiceImpl(GeminiService geminiService, ChatHistoryRepository chatHistoryRepository, UserRepository userRepository, PromptBuilder promptBuilder) {
        this.geminiService = geminiService;
        this.chatHistoryRepository = chatHistoryRepository;
        this.userRepository = userRepository;
        this.promptBuilder = promptBuilder;
    }

    @Override
    public ChatResponse askQuestion(ChatRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String securePrompt = promptBuilder.buildAyurvedicPrompt(request.getSymptoms());
        String aiResponse = geminiService.generateAyurvedicResponse(securePrompt);

        ChatHistory chatHistory = ChatHistory.builder()
                .user(user)
                .userPrompt(request.getSymptoms())
                .aiResponse(aiResponse)
                .build();

        chatHistoryRepository.save(chatHistory);

        return new ChatResponse(aiResponse);
    }

    @Override
    public List<ChatHistory> getChatHistory(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return chatHistoryRepository.findByUserIdOrderByTimestampDesc(user.getId());
    }
}