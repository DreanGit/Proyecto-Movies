package com.fpmislata.movies.bussines.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;

    public Director (String name, int yearBirth, int yearDeath) {
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
    }

    public Director(String name, int yearBirth, int yearDeath, int id) {
        this.name = name;
        this.birthYear = yearBirth;
        this.deathYear = yearDeath;
        this.id = id;
    }

    public Director() {
    }

    @Override
    public String toString() {
        return "Director [id=" + id + ", name=" + name + ", yearBirth=" + birthYear + ", yearDeath=" + deathYear + "]";
    }
}
