package ru.berdnikov.avitohunterbot.service;

public interface LinkModificationService {
    String addNewLink(long profileId, String input);

    String deleteLink(long profileId, String input);
}
