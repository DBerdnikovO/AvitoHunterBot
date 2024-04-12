package ru.berdnikov.avitohunterbot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "link")
public class Link {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "link")
    private String link;

    @Column(name = "profile_id")
    private long profile_id;

    @Column(name = "name")
    private String name;

    public Link() {
    }

    public Link(String link, long profile_id, String name) {
        this.link = link;
        this.profile_id = profile_id;
        this.name = name;
    }
}

