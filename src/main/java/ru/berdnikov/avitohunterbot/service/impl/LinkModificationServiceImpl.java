package ru.berdnikov.avitohunterbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.berdnikov.avitohunterbot.entity.Link;
import ru.berdnikov.avitohunterbot.entity.Profile;
import ru.berdnikov.avitohunterbot.service.LinkModificationService;
import ru.berdnikov.avitohunterbot.service.LinkService;
import ru.berdnikov.avitohunterbot.service.ProfileService;
import ru.berdnikov.avitohunterbot.util.errors.Errors;

import static ru.berdnikov.avitohunterbot.util.http.Urls.AVITO_BASE_URL;
import static ru.berdnikov.avitohunterbot.util.http.Urls.AVITO_MOBILE;

@Service
public class LinkModificationServiceImpl implements LinkModificationService {
    private final ProfileService profileService;
    private final LinkService linkService;

    @Autowired
    public LinkModificationServiceImpl(ProfileService profileService, LinkService linkService) {
        this.profileService = profileService;
        this.linkService = linkService;
    }

    public String addNewLink(long profileId, String input) {
        if (!input.matches(".+: .+")) {
            return Errors.LINK_INPUT_ERROR;
        }

        // Разбиение ввода на название и ссылку
        String[] parts = input.split(": ");
        String name = parts[0];
        String link = parts[1];

        // Проверка на валидность ссылки
        if (!link.startsWith(AVITO_BASE_URL)) {
            if (!link.startsWith(AVITO_MOBILE)) return Errors.LINK_FORMAT;
        }
        Profile profile = profileService.getProfile(profileId);
        if (profile == null) {
            profile = new Profile(profileId);
            profileService.addNewProfile(profile);
        }
        if (linkService.isLinkExists(profileId, link)) {
            linkService.addLink(new Link(link, profileId, name));
            return "Это ваша ссылка: " + name + " - " + link;
        } else {
            return Errors.LINK_ALREADY_TAKEN;
        }
    }

    public String deleteLink(long chatId, String input) {
        String link = input.split(": ", 2)[1].trim();
        Profile profile = profileService.getProfile(chatId);
        if (profile == null) {
            return Errors.USER_NOT_FOUND;
        }
        if (linkService.isLinkExists(chatId, link)) {
            linkService.deleteLink(chatId, link);
            return "Ссылка \"" + link + "\" успешно удалена.";
        } else {
            return "Ссылка с названием \"" + link + "\" не найдена.";
        }
    }
}
