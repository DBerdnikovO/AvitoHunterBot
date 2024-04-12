package ru.berdnikov.avitohunterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.avitohunterbot.entity.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
}
