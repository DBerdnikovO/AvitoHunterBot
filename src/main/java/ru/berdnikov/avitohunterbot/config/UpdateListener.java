package ru.berdnikov.avitohunterbot.config;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateListener {
    void onUpdateReceived(Update update);
}
