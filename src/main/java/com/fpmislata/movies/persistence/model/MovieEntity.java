package com.fpmislata.movies.persistence.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
 
    private int id;
    private String title;
    private int year;
    private int runtime;
    private int directorId;         // int
    private List<Integer> actorIds; // lista de integers
 
 
    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
} //como habrá veces que queramos devolver los campos de una película sin el director ni los actores, hemos creado un constructor sin esos atributos

