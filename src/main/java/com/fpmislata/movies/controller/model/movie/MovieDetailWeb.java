package com.fpmislata.movies.controller.model.movie;

import java.util.List;

import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {
 
    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;  // con id y name del director
    private List<ActorListWeb> actors;  // lista de ids y names de los actores
}

