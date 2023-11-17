package com.fpmislata.movies.controller.model.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieListWeb {
    private int id;
    private String title;

    //Constructores

    public MovieListWeb() {

    }
    
    public MovieListWeb(int id, String title) {
        this.id = id;
        this.title = title;
    }
}