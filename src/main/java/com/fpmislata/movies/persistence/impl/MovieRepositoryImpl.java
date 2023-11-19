package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repo.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.MovieEntity;

import java.util.List;
import java.util.Optional;

import javax.print.event.PrintJobListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

import java.sql.SQLException;


@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> pageSize) {  // estos métodos son los que implementa la interface Repository que ahora esta en domain

        try (Connection connection = DBUtil.open(true)){
            
            List<MovieEntity> movieEntities = movieDAO.getAll(connection, page, pageSize);  // 3- obtengo la lista de movieEntities del DAO y luego la mapeo a Movie 
            List<Movie> movies = movieEntities.stream()
                        .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
                        .toList();

            return movies;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
 
    @Override
    public Optional<Movie> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            
            Optional<MovieEntity> movieEntity = movieDAO.find(connection, id);  // Se trae la pelicula por id 
            if(movieEntity.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get())); // y si no esta vacio lo mapea a un objeto Movie y lo devuelve en un optional
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try (Connection connection = DBUtil.open(true)){

                return movieDAO.getTotalNumberOfRecords(connection); // obtener el número de registros de movieDAO
                
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int insert(Movie movie) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int id = movieDAO.insert(connection, movieEntity);
            return id;
     
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 
    
}
