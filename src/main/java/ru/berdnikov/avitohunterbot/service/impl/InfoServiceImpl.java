package ru.berdnikov.avitohunterbot.service.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.berdnikov.avitohunterbot.service.InfoService;
import ru.berdnikov.avitohunterbot.util.info.Info;

import static ru.berdnikov.avitohunterbot.util.info.Info.requestMessage;

@Component
public class InfoServiceImpl implements InfoService {
    @Override
    public SendMessage addLinkInfo(long chatId) {
        return requestMessage(chatId, Info.ADD_LINK_INFO);
    }

    @Override
    public SendMessage deleteLinkInfo(long chatId) {
        return requestMessage(chatId, Info.DELETE_LINK_INFO);
    }

    @Override
    public SendMessage sayHello(long chatId) {
        return requestMessage(chatId, Info.HELLO_STRING);
    }
}
