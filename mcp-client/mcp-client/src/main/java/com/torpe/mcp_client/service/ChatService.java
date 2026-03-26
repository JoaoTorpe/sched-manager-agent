package com.torpe.mcp_client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ChatService {

    private final ChatClient client;
    private final PromptService promptService;

    public ChatService(ChatClient client, PromptService promptService) {
        this.client = client;
        this.promptService = promptService;
    }

    public String chat (String input) throws IOException {
        LocalDate today = LocalDate.now();
        String systemPrompt = promptService
                .loadPrompt("chat")
                .replace("{{today}}", today.toString());

        return client.prompt()
                .system(systemPrompt)
                .user(input)
                .call()
                .content();
    }
}
