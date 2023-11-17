package com.fpmislata.movies.persistence.impl;

import org.springframework.stereotype.Repository;

import com.fpmislata.movies.bussines.entity.Director;
import com.fpmislata.movies.bussines.repo.DirectorRepository;
import com.fpmislata.movies.controller.model.director.DirectorWeb;
import com.fpmislata.movies.db.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository{

    @Override
    public int insert(Director director) {

        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());

        try (Connection connection = DBUtil.open()) {
            int id = DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public Director findDirector(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open()) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            // DBUtil.closeConnection(connection);
            if (resultSet.next()) {
                //crear nuevo objeto dto
                Director director = new Director();
                director.setId(resultSet.getInt("id"));
                director.setName(resultSet.getString("name"));
                director.setBirthYear(resultSet.getInt("birthYear"));
                int deathYear = resultSet.getInt("deathYear");
            if (resultSet.wasNull()) {
                director.setDeathYear(null);
            } else {
                director.setDeathYear(deathYear);
            }
                 return director;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Director director) {
        final String SQL = "UPDATE director SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            List<Object> params = new ArrayList<>();
            params.add(director.getName());
            params.add(director.getBirthYear());
            params.add(director.getDeathYear());
            params.add(director.getId());

            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void delete(int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open()) {
            DBUtil.delete(connection, SQL, List.of(id));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}