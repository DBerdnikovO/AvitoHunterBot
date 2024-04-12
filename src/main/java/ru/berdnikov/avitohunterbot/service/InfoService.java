package ru.berdnikov.avitohunterbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface InfoService {
    SendMessage addLinkInfo(long profileId);

    SendMessage deleteLinkInfo(long profileId);

    SendMessage sayHello(long profileId);
}
