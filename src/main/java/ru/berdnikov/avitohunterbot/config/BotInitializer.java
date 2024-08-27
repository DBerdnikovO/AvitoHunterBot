package ru.berdnikov.avitohunterbot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.berdnikov.avitohunterbot.controller.AvitoHunterController;
import ru.berdnikov.avitohunterbot.excepton.TelegramInitException;

@Component
@RequiredArgsConstructor
public class BotInitializer {
    private final AvitoHunterController avitoHunterController;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(avitoHunterController);
        } catch (TelegramApiException e) {
            throw new TelegramInitException(e.getMessage());
        }
    }
}
