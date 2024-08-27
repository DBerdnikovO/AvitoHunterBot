package ru.berdnikov.avitohunterbot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {
    @Value("${bot.name}")
    public String botName;
    @Value("${bot.token}")
    public String token;
}
