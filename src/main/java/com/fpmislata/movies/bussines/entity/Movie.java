package com.fpmislata.movies.bussines.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private int year;
    private int runTime;
    private Director director;
    private List<Actor> actors;

    public Movie (int id, String title, int year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }

    public Movie(int id, String title, int year, int runTime, Director director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.director = director;
        
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", runTime=" + runTime ;
    }
    
}
