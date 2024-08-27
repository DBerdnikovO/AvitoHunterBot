package ru.berdnikov.avitohunterbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.berdnikov.avitohunterbot.entity.Link;
import ru.berdnikov.avitohunterbot.service.*;
import ru.berdnikov.avitohunterbot.util.errors.Errors;
import ru.berdnikov.avitohunterbot.util.info.Info;

import java.util.List;

import static ru.berdnikov.avitohunterbot.util.info.Info.requestMessage;

@Service
public class CommandBotServiceImpl implements CommandBotService {
    private final ProfileService profileService;
    private final LinkService linkService;
    private final LinkModificationService linkModificationService;
    private final TaskService taskService;

    @Autowired
    public CommandBotServiceImpl(ProfileService profileService, LinkService linkService, LinkModificationService linkModificationService, TaskService taskService) {
        this.profileService = profileService;
        this.linkService = linkService;
        this.linkModificationService = linkModificationService;
        this.taskService = taskService;
    }

    @Override
    public SendMessage modificationLink(long profileId, String input) {
        String[] parts = input.split(": ", 2);
        String command = parts[0].trim();
        if (command.equals("Удалить")) {
            return requestMessage(profileId, linkModificationService.deleteLink(profileId, input));
        }
        return requestMessage(profileId, linkModificationService.addNewLink(profileId, input));
    }

    @Override
    public SendMessage listOfLinks(long profileId) {
        SendMessage errorMessage = checkProfileAndLinks(profileId);
        if (errorMessage != null) {
            return errorMessage;
        }
        return requestMessage(profileId, profileLinks(profileId));
    }

//    @Override
//    public SendMessage startSearchLinks(long profileId, AvitoHunterTelegramPollingBot avitoHunterTelegramPollingBot) {
//        SendMessage errorMessage = checkProfileAndLinks(profileId);
//        if (errorMessage != null) {
//            return errorMessage;
//        }
//        return requestMessage(profileId, taskService.startSearchTask(profileId, avitoHunterTelegramPollingBot));
//    }

    @Override
    public SendMessage stopSearchLinks(long chatId) {
        return requestMessage(chatId, taskService.stopSearchTask());
    }

    private SendMessage checkProfileAndLinks(long profileId) {
        if (profileService.isProfileExist(profileId)) {
            return requestMessage(profileId, Errors.USER_NOT_FOUND);
        }
        if (linkService.getLinksAsAList(profileId).isEmpty()) {
            return requestMessage(profileId, Errors.LINKS_NOT_FOUND);
        }
        return null;
    }

    private String profileLinks(long profileId) {
        List<Link> links = linkService.getLinksAsAList(profileId);
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(Info.YOUR_LINKS);
        for (Link link : links) {
            messageBuilder.append(link.getName()).append(": ").append(link.getLink()).append("\n");
        }
        return messageBuilder.toString();
    }
}
