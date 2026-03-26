package com.torpe.mcp_client.service;

import com.torpe.mcp_client.config.TelegramProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class MessageService {

    private final TelegramClient telegramClient;
    private final TelegramProperties telegramProperties;

    public MessageService(TelegramClient telegramClient, TelegramProperties telegramProperties) {
        this.telegramClient = telegramClient;
        this.telegramProperties = telegramProperties;
    }

    public void sendMessage(String message){
        try {
            telegramClient.execute(new SendMessage(telegramProperties.getChannel(),message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
