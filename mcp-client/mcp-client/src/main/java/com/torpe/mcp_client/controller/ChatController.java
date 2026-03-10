package com.torpe.mcp_client.controller;

import com.torpe.mcp_client.service.PromptService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient client;
    private final PromptService promptService;

    public ChatController(ChatClient client, PromptService promptService) {
        this.client = client;
        this.promptService = promptService;
    }

    @PostMapping
    public String chat(@RequestBody String input) throws IOException {
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
