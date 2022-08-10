package com.example.interview.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "name_links")
public class LinkNameEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Setter
    @Getter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Column(name = "link")
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
