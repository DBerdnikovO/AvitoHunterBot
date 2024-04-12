package ru.berdnikov.avitohunterbot.util.info;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Info {
    public static final String HELLO_STRING = "Это чат-бот для отслеживания новых товаров по запросу " +
            "на Авито";
    public static final String START_INFO = "Начался процесс отслеживаний объявлений";
    public static final String STOP_INFO = "Процесс отслеживаний объявлений прекратился";
    public static final String STOP_NOT_FOUND_INFO = "Не найден процесс отслеживания";
    public static final String ADD_LINK_INFO = "Добавить ссылку можно в следующем формате: ИмяСсылки: СсылкаАвито";
    public static final String DELETE_LINK_INFO = "Удалить ссылку можно в следующем формате: Удалить: НазваниеСсылки";

    public static final String YOUR_LINKS = "Ваши ссылки:\n";

    public static SendMessage requestMessage(long chatId, String messageStr) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageStr);
        return message;
    }
}
