package com.torpe.mcp_client.config;

import com.torpe.mcp_client.model.TelegramConsumer;
import com.torpe.mcp_client.service.ChatService;
import com.torpe.mcp_client.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TelegramBotConfiguration {

    private final TelegramProperties telegramProperties;

    public TelegramBotConfiguration(TelegramProperties telegramProperties) {
        this.telegramProperties = telegramProperties;
    }

    @Bean
    public BotSession telegramBotSession(TelegramConsumer telegramConsumer) {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            return botsApplication.registerBot(telegramProperties.getToken(), telegramConsumer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public TelegramClient telegramClient()
    {
        return new OkHttpTelegramClient(telegramProperties.getToken());
    }
}
