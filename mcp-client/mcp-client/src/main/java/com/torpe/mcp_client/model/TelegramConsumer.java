package com.torpe.mcp_client.model;

import com.torpe.mcp_client.service.ChatService;
import com.torpe.mcp_client.service.TelegramMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
public class TelegramConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final ChatService chatService;
    private final TelegramMessageService telegramMessageService;

    public TelegramConsumer(ChatService chatService, TelegramMessageService telegramMessageService) {
        this.chatService = chatService;
        this.telegramMessageService = telegramMessageService;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
              String chatResult = chatService.chat(update.getMessage().getText());
              telegramMessageService.sendMessage(chatResult);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
