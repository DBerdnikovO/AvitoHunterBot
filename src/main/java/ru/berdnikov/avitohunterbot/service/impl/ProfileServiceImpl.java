package ru.berdnikov.avitohunterbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.berdnikov.avitohunterbot.entity.Profile;
import ru.berdnikov.avitohunterbot.repository.ProfileRepo;
import ru.berdnikov.avitohunterbot.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepo profileRepo;

    @Autowired
    public ProfileServiceImpl(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    @Transactional
    public void addNewProfile(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public Profile getProfile(long profileId) {
        return profileRepo.findById(profileId).orElse(null);
    }

    @Override
    public boolean isProfileExist(long profileId) {
        return !profileRepo.existsById(profileId);
    }
}
