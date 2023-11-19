package com.fpmislata.movies.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.model.ActorEntity;

@Component
public class ActorDAO {

    public List<ActorEntity> getAll(Connection connection) {

        String sql = "SELECT * FROM actors"; // SQL sin cláusula LIMIT
    
        List<ActorEntity> actorsEntity = new ArrayList<>(); // 1- creo una lista de actorsEntity
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                actorsEntity.add(ActorMapper.mapper.toActorEntity(resultSet)); // 2- relleno la lista de resultados resultSet que mapeo a actorsEntity
            }
            return actorsEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e); // Es buena práctica proporcionar la excepción original como causa
        }
    }
       

    public int insert(Connection connection, ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public Optional<ActorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()? ActorMapper.mapper.toActorEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    
    public void update(Connection connection, ActorEntity actorEntity) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        params.add(actorEntity.getId());
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    public List<ActorEntity> findActorsByMovieId(Connection connection ,int id){
        final String SQL = """
                    SELECT a.* FROM actors a
                    INNER JOIN actors_movies am ON am.actor_id = a.id
                    INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
                """;

        List<ActorEntity> actorEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection,SQL,List.of(id)); // id de la pelicula
            while (resultSet.next()){
                actorEntities.add(ActorMapper.mapper.toActorEntity(resultSet));
            }
            return actorEntities;
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }
    
}






















 /*
Antes tenía esto:

 @Override
    public int insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        Connection connection = DBUtil.open(true);
        int id = DBUtil.insert(connection, SQL, params);
        DBUtil.close(connection);
        return id;
    }

    @Override
    public Optional<Actor> find(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Actor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Actor update(Actor actor) { // como quiero que me devuelva el actor que modifique cambio de void a Actor
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        Connection connection = DBUtil.open(true);
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);

        return actor; //añado el return actor;
    }

    @Override
    public void delete(int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        Connection connection = DBUtil.open(true);
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);

    }


    @Override
    public List<Actor> findActorsByMovieId(int id) {
        final String SQL ="""
            SELECT a.* FROM actors a
            INNER JOIN actors_movies am ON am.actor_id = a.id
            INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
        """;
        try (Connection connection = DBUtil.open(true)){
            List<Actor> actor = new ArrayList<>();
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            while (resultSet.next()){
                actor.add(
                        new Actor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                        )
                );
            }
            DBUtil.close(connection);
            return actor;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
 */