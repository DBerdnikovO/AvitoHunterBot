package ru.berdnikov.avitohunterbot.service;

import ru.berdnikov.avitohunterbot.entity.Profile;

public interface ProfileService {
    void addNewProfile(Profile profile);

    Profile getProfile(long profileId);

    boolean isProfileExist(long profileId);
}
