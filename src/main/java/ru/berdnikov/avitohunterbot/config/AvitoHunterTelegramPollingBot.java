package ru.berdnikov.avitohunterbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AvitoHunterTelegramPollingBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private UpdateListener updateListener;

    @Autowired
    public AvitoHunterTelegramPollingBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    public void registerUpdateListener(UpdateListener listener) {
        this.updateListener = listener;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (updateListener != null) {
            updateListener.onUpdateReceived(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
