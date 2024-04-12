package ru.berdnikov.avitohunterbot.service;

import ru.berdnikov.avitohunterbot.config.AvitoHunterTelegramPollingBot;

public interface TaskService {
    String startSearchTask(long profileId, AvitoHunterTelegramPollingBot avitoHunterTelegramPollingBot);

    String stopSearchTask();
}
