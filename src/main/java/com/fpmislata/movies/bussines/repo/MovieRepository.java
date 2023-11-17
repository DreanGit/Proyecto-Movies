package com.fpmislata.movies.bussines.repo;

import com.fpmislata.movies.bussines.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> findMovies(Optional<Integer> page);

    Integer getTotalRecords();
    
    Movie findMoviesById(int id);

    void insertMovies(Movie movies);

    void deleteMovie(int id);
}
