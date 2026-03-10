package com.torpe.mcp_client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class NotificationService {

    private final ChatClient client;

    private final PromptService promptService;

    public NotificationService(ChatClient client, PromptService promptService) {
        this.client = client;
        this.promptService = promptService;
    }

    @Scheduled(cron = "0 0 6 * * MON", zone = "America/Sao_Paulo")
    public void MondayNotification() throws IOException {
        sendNotification("monday");
    }

    @Scheduled(cron = "0 0 23 * * FRI", zone = "America/Sao_Paulo")
    public void FridayNotification() throws IOException {
        sendNotification("friday");
    }

    private void sendNotification(String promptKey) throws IOException {
        LocalDate today = LocalDate.now();

        String prompt = promptService
                .loadPrompt(promptKey)
                .replace("{{today}", today.toString());

        String result = client.prompt()
                .system(prompt)
                .call()
                .content();
    }
}
