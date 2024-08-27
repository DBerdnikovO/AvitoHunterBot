package ru.berdnikov.avitohunterbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.berdnikov.avitohunterbot.config.BotCommands;
import ru.berdnikov.avitohunterbot.config.BotConfig;
import ru.berdnikov.avitohunterbot.service.CommandBotService;
import ru.berdnikov.avitohunterbot.service.InfoService;

@Component
@RequiredArgsConstructor
public class AvitoHunterController extends TelegramLongPollingBot {
    private final CommandBotService commandBotService;
    private final InfoService infoService;
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.botName;
    }
    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                long profileId = update.getMessage().getChatId();
                String receivedMessage = update.getMessage().getText();
                execute(processCommand(profileId, receivedMessage));
            } else if (update.hasCallbackQuery()) {
                long profileId = update.getCallbackQuery().getMessage().getChatId();
                String receivedMessage = update.getCallbackQuery().getData();
                execute(processCommand(profileId, receivedMessage));
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private SendMessage processCommand(long profileId, String receivedMessage) {
        switch (receivedMessage) {
            case BotCommands.HELLO_COMMAND -> {
                return infoService.sayHello(profileId);
            }
            case BotCommands.LINK_COMMAND -> {
                return commandBotService.listOfLinks(profileId);
            }
            case BotCommands.ADD_COMMAND -> {
                return infoService.addLinkInfo(profileId);
            }
            case BotCommands.DELETE_COMMAND -> {
                return infoService.deleteLinkInfo(profileId);
            }
//            case "/go" -> {
//                return commandBotService.startSearchLinks(profileId, this);
//            }
            case BotCommands.STOP_COMMAND -> {
                return commandBotService.stopSearchLinks(profileId);
            }
            default -> {
                return commandBotService.modificationLink(profileId, receivedMessage);
            }
        }
    }
}
