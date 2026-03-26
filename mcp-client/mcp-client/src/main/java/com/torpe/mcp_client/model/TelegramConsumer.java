package com.torpe.mcp_client.model;

import com.torpe.mcp_client.service.ChatService;
import com.torpe.mcp_client.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
public class TelegramConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final ChatService chatService;
    private final MessageService messageService;

    public TelegramConsumer(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
              String chatResult = chatService.chat(update.getMessage().getText());
              messageService.sendMessage(chatResult);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
