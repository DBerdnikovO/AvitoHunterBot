package ru.berdnikov.avitohunterbot.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface TaskService {
    String startSearchTask(long profileId, TelegramLongPollingBot avitoHunterTelegramPollingBot);

    String stopSearchTask();
}
