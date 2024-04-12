package ru.berdnikov.avitohunterbot.config;

import org.springframework.beans.factory.annotation.Value;

abstract class Configuration {
    @Value("${bot.name}")
    public String botName;
    @Value("${bot.token}")
    public String token;
}
