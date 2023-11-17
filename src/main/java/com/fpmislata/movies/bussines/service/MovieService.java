package com.fpmislata.movies.bussines.service;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.bussines.entity.Movie;

public interface MovieService {

    List<Movie> findMovies(Optional<Integer> page);

    Integer getTotalRecords();

    Movie findMoviesById(int id);

    void insertMovies(Movie movies);

    void deleteMovie(int id);
    
}
