package com.fpmislata.movies.bussines.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Actor {
    private String name;
    private int yearBirth;
    private int yearDeath;
    private int id;

    public Actor (String name, int yearBirth, int yearDeath) {
        this.name = name;
        this.yearBirth = yearBirth;
        this.yearDeath = yearDeath;
    }

    public Actor(String name, int yearBirth, int yearDeath, int id) {
        this.name = name;
        this.yearBirth = yearBirth;
        this.yearDeath = yearDeath;
        this.id = id;
    }

    public Actor() {
    }
}
