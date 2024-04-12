package ru.berdnikov.avitohunterbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.berdnikov.avitohunterbot.config.AvitoHunterTelegramPollingBot;
import ru.berdnikov.avitohunterbot.config.UpdateListener;
import ru.berdnikov.avitohunterbot.service.CommandBotService;
import ru.berdnikov.avitohunterbot.service.InfoService;

@Component
public class AvitoHunterController implements UpdateListener {
    private final AvitoHunterTelegramPollingBot avitoHunterTelegramPollingBot;
    private final CommandBotService commandBotService;
    private final InfoService infoService;


    @Autowired
    public AvitoHunterController(AvitoHunterTelegramPollingBot avitoHunterTelegramPollingBot, CommandBotService commandBotService, InfoService infoService) {
        this.avitoHunterTelegramPollingBot = avitoHunterTelegramPollingBot;
        this.commandBotService = commandBotService;
        this.infoService = infoService;
        avitoHunterTelegramPollingBot.registerUpdateListener(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        long profileId;
        String receivedMessage;
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                profileId = update.getMessage().getChatId();
                receivedMessage = update.getMessage().getText();
                avitoHunterTelegramPollingBot.execute(processCommand(profileId, receivedMessage));
            } else if (update.hasCallbackQuery()) {
                profileId = update.getCallbackQuery().getMessage().getChatId();
                receivedMessage = update.getCallbackQuery().getData();
                avitoHunterTelegramPollingBot.execute(processCommand(profileId, receivedMessage));
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private SendMessage processCommand(long profileId, String receivedMessage) throws TelegramApiException {
        switch (receivedMessage) {
            case "/hello" -> {
                return infoService.sayHello(profileId);
            }
            case "/link" -> {
                return commandBotService.listOfLinks(profileId);
            }
            case "/add" -> {
                return infoService.addLinkInfo(profileId);
            }
            case "/delete" -> {
                return infoService.deleteLinkInfo(profileId);
            }
            case "/go" -> {
                return commandBotService.startSearchLinks(profileId, avitoHunterTelegramPollingBot);
            }
            case "/stop" -> {
                return commandBotService.stopSearchLinks(profileId);
            }
            default -> {
                return commandBotService.modificationLink(profileId, receivedMessage);
            }
        }
    }
}
