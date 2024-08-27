//package ru.berdnikov.avitohunterbot.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@Component
//@RequiredArgsConstructor
//public class AvitoHunterTelegramPollingBot extends TelegramLongPollingBot {
//    private final BotConfig botConfig;
//    private UpdateListener updateListener;
//
//    @Override
//    public String getBotUsername() {
//        return botConfig.getBotName();
//    }
//
//    @Override
//    public String getBotToken() {
//        return botConfig.getToken();
//    }
//
//    public void registerUpdateListener(UpdateListener listener) {
//        this.updateListener = listener;
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (updateListener != null) {
//            updateListener.onUpdateReceived(update);
//        }
//    }
//}
