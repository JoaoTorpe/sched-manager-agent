package com.torpe.mcp_client.service;

import com.torpe.mcp_client.config.TelegramProperties;
import com.torpe.mcp_client.service.interfaces.MessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TelegramMessageService implements MessageService {

    private final TelegramClient telegramClient;
    private final TelegramProperties telegramProperties;

    public TelegramMessageService(TelegramClient telegramClient, TelegramProperties telegramProperties) {
        this.telegramClient = telegramClient;
        this.telegramProperties = telegramProperties;
    }

    @Override
    public void sendMessage(String message){
        try {
            telegramClient.execute(new SendMessage(telegramProperties.getChannel(),message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
