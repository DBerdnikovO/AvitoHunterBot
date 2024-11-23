package ru.berdnikov.avitohunterbot.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandBotService {
    SendMessage startSearchLinks(long profileId, TelegramLongPollingBot avitoHunterTelegramPollingBot);

    SendMessage modificationLink(long profileId, String input);

    SendMessage listOfLinks(long profileId);

    SendMessage stopSearchLinks(long profileId);
}
