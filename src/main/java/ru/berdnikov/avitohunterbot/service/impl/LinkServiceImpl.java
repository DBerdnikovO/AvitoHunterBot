package ru.berdnikov.avitohunterbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.berdnikov.avitohunterbot.entity.Link;
import ru.berdnikov.avitohunterbot.repository.LinkRepo;
import ru.berdnikov.avitohunterbot.service.LinkService;

import java.util.List;


@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepo linkRepo;

    @Autowired
    public LinkServiceImpl(LinkRepo linkRepo) {
        this.linkRepo = linkRepo;
    }

    public void deleteLink(long profileId, String name) {
        Link link = linkRepo.findLinkByNameAndProfileId(profileId, name);
        linkRepo.delete(link);
    }

    @Override
    public void addLink(Link link) {
        linkRepo.save(link);
    }

    @Override
    public boolean isLinkExists(long profileId, String link) {
        return !linkRepo.existsLinkByNameAndProfileId(profileId, link);
    }

    @Override
    public List<Link> getLinksAsAList(long profileId) {
        return linkRepo.findAllByProfileId(profileId).orElse(null);
    }
}
