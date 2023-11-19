package com.fpmislata.movies.controller.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieListWeb {  // cuando con el mapper convierta a está clase (model web) se mostrará solo el id y el titulo de la película
 
    private int id;
    private String title;
 
}
