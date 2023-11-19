package com.fpmislata.movies.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.model.MovieEntity;



@Component
public class MovieDAO {
    public List<MovieEntity> getAll(Connection connection, Optional<Integer> page, Optional<Integer> pageSize) {

        String sql = "SELECT * FROM movies";         

        if (page.isPresent()) {
            int offset = (page.get() - 1) * pageSize.get();
            sql += String.format(" LIMIT %d, %d", offset, pageSize.get());
        }    

        List<MovieEntity> moviesEntity = new ArrayList<>();                    // 1- creo una lista de moviesEntity
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                moviesEntity.add(MovieMapper.mapper.toMovieEntity(resultSet)); // 2- relleno la lista de resultados resultSet que mapeo a moviesEntity
            }
            return moviesEntity;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public Optional<MovieEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
           
            return Optional.ofNullable(resultSet.next()? MovieMapper.mapper.toMovieEntity(resultSet):null);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);

            resultSet.next();
            return (int) resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    } 

    
    public void addActor(Connection connection, int movieId, int actorId) {
        final String SQL = "INSERT INTO actors_movies (actor_id, movie_id) VALUES (?, ?)";
        DBUtil.insert(connection, SQL, List.of(actorId, movieId));
    }

    public int insert(Connection connection, MovieEntity movieEntity) throws SQLException {
        try {
            final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(movieEntity.getTitle());
            params.add(movieEntity.getYear());
            params.add(movieEntity.getRuntime());
            params.add(movieEntity.getDirectorId());
            int id = DBUtil.insert(connection, SQL, params);

            //insertar los actores asociados a la pelicula
            movieEntity.getActorIds().stream()                                //recordar que en el modelo Entity tengo actorsIds
                    .forEach(actorId -> addActor(connection, id, actorId));
                    //.forEach(actorId -> addActor(connection, 0, actorId));
            connection.commit();
            return id;

        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

}


