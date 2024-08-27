package ru.berdnikov.avitohunterbot.excepton;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author danilaberdnikov on TelegramInitException.
 * @project AvitoHunterBot
 */
public class TelegramInitException extends TelegramApiException {
    public TelegramInitException() {
    }

    public TelegramInitException(String message) {
        super(message);
    }

    public TelegramInitException(Throwable cause) {
        super(cause);
    }
}
