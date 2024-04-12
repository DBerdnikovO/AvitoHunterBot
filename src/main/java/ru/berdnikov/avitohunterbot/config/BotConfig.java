package ru.berdnikov.avitohunterbot.config;

import org.springframework.stereotype.Component;

@Component
public class BotConfig extends Configuration implements ConfigurationConfig {
    @Override
    public String getBotName() {
        return botName;
    }

    @Override
    public String getToken() {
        return token;
    }
}
