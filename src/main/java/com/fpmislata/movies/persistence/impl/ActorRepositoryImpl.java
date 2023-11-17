package com.fpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fpmislata.movies.bussines.entity.Director;
import com.fpmislata.movies.bussines.repo.ActorRepository;
import com.fpmislata.movies.bussines.entity.Actor;
import com.fpmislata.movies.db.DBUtil;

import org.springframework.stereotype.Repository;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public void insertActor(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(actor.getName());
            params.add(actor.getYearBirth());
            params.add(actor.getYearDeath());

            try(Connection connection = DBUtil.open()) {
                DBUtil.insert(connection, SQL, params);
                DBUtil.close(connection);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
    }
    
    public Actor findActor(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open()) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            // DBUtil.closeConnection(connection);
            if (resultSet.next()) {
                return new Actor(
                    resultSet.getString("name"),
                    resultSet.getInt("yearBirth"),
                    resultSet.getInt("yearDeath"),
                    resultSet.getInt("id")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Actor actor) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ?, WHERE id = ?";
        try (Connection connection = DBUtil.open()) {
            List<Object> params = new ArrayList<>();
            params.add(actor.getName());
            params.add(actor.getYearBirth());
            params.add(actor.getYearDeath());
            params.add(actor);

            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
