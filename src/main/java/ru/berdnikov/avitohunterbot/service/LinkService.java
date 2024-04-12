package ru.berdnikov.avitohunterbot.service;

import ru.berdnikov.avitohunterbot.entity.Link;

import java.util.List;

public interface LinkService {
    void deleteLink(long profileId, String link);

    void addLink(Link link);

    boolean isLinkExists(long profileId, String link);

    List<Link> getLinksAsAList(long profileId);
}
