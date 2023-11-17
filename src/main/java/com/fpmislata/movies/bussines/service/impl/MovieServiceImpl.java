package com.fpmislata.movies.bussines.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.bussines.entity.Movie;
import com.fpmislata.movies.bussines.repo.MovieRepository;
import com.fpmislata.movies.bussines.service.MovieService;
import com.fpmislata.movies.exceptions.ResourceNotFoundException;


@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository moviesRepository;

    @Override
    public List<Movie> findMovies(Optional<Integer> page) {
        return moviesRepository.findMovies(page);
    }

    @Override
    public Movie findMoviesById(int id) {
    //    return moviesRepository.findMoviesById(id);
    Movie movie = moviesRepository.findMoviesById(id);
        if(movie==null){
            throw new ResourceNotFoundException("Movie no encontrada");
        }
    return movie;
    }

    @Override
    public void insertMovies(Movie movies) {
       moviesRepository.insertMovies(movies);
    }

    @Override
    public void deleteMovie(int id) {
        
        moviesRepository.deleteMovie(id);   
 }

    @Override
    public Integer getTotalRecords() {
       return moviesRepository.getTotalRecords();
    }
}
