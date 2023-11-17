package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.exceptions.DBConnectionException;
import com.fpmislata.movies.exceptions.ResourceNotFoundException;
import com.fpmislata.movies.exceptions.SQLStatementException;
import com.fpmislata.movies.bussines.entity.Movie;
import com.fpmislata.movies.bussines.repo.MovieRepository;
import com.fpmislata.movies.bussines.entity.Director;

import java.sql.*;
import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Repository;

@Repository
public class MovieImpl implements MovieRepository {
    private final int LIMIT = 10;

    @Override
    public List<Movie> findMovies(Optional<Integer> page) {
        try (Connection connection = DBUtil.open()) {
            String SQL = "SELECT * FROM movies";
            if (page.isPresent()) {
                int offset = (page.get() - 1) * LIMIT;
                SQL += String.format(" LIMIT %d, %d", offset, LIMIT);
            }
            
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            List<Movie> movies = new ArrayList<>();

            while (resultSet.next()) {
                movies.add(new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getInt("runTime")
                ));
            }
            return movies;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Movie findMoviesById(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ?";
        try (Connection connection = DBUtil.open()) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            //DBUtil.closeConnection(connection);
            if(resultSet.next()) {
                return new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getInt("runtime")
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }

    @Override
    public void insertMovies(Movie movies) {

        try (Connection connection = DBUtil.open()) {

            String SQL = "INSERT INTO movies (id,title,year,runTime  VALUES (?, ?, ?, ?)";

            List<Object> params = new ArrayList<>(Arrays.asList(
                    movies.getId(),
                    movies.getTitle(),
                    movies.getYear(),
                    movies.getRunTime()));
            DBUtil.insert(connection, SQL, params);
        } catch (Exception e) {

        }
    }
    
        @Override
    public void deleteMovie(int id) {
        try (Connection connection = DBUtil.open()) {
            String SQL = "DELETE from movies where id=?";

            DBUtil.delete(connection, SQL, List.of(id));
        } catch (Exception e) {
            System.out.println("insertMovies");
        }
    }

    @Override
    public Integer getTotalRecords() {
        try (Connection connection = DBUtil.open()) {

            String SQL = "SELECT COUNT(*) from movies";
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Algo va mal");
        }
    }
}
