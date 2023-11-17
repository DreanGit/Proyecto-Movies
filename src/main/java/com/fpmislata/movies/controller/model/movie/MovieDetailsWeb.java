package com.fpmislata.movies.controller.model.movie;



import java.util.List;

import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;

// import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;



@Getter
@Setter
@NoArgsConstructor
public class MovieDetailsWeb {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    private List<ActorListWeb> actors;

    // Constructor, getters y setters
    // Inner classes para DirectorDTO y ActorDTO si es necesario
}
