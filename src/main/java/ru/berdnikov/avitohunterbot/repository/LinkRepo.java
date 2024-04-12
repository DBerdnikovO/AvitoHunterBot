package ru.berdnikov.avitohunterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.berdnikov.avitohunterbot.entity.Link;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepo extends JpaRepository<Link, Long> {
    @Query("SELECT l from Link l where l.profile_id = ?1")
    Optional<List<Link>> findAllByProfileId(long profileId);

    @Query("SELECT COUNT(l) > 0 FROM Link l WHERE l.profile_id = ?1 AND l.link = ?2")
    boolean existsLinkByNameAndProfileId(long profileId, String link);

    @Query("SELECT l FROM Link l WHERE l.profile_id = ?1 AND l.name = ?2")
    Link findLinkByNameAndProfileId(long profileId, String name);
}
