package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repo.ActorRepository;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;
import com.fpmislata.movies.persistence.model.MovieEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de actores.
 * Proporciona la conexión entre la capa de persistencia y la lógica de negocio,
 * utilizando ActorDAO para interactuar con la base de datos.
 */
@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Actor> getAll() {  // estos métodos son los que implementa la interface Repository que ahora esta en domain

        try (Connection connection = DBUtil.open(true)){

            // Asegúrate de que actorDAO.getAll() no requiere parámetros y devuelve todos los actores.
            List<ActorEntity> actorEntities = actorDAO.getAll(connection);  // 3- obtengo la lista de actorEntities del DAO y luego la mapeo a Actor 
            List<Actor> actors = actorEntities.stream()
                        .map(actorEntity -> ActorMapper.mapper.toActor(actorEntity))
                        .collect(Collectors.toList()); // Usa collect(Collectors.toList()) en lugar de .toList() si no estás en Java 16 o superior.

            return actors;

        } catch (SQLException e) {
            throw new RuntimeException(e); // Es buena práctica pasar la excepción original como causa.
        }
    }


    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Actor> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDAO.find(connection,id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.ofNullable(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Intenta actualizar un actor en la base de datos.
     * Error: The return type is incompatible with ActorRepository.update(Actor)
     * La interfaz ActorRepository define que el método update debe ser void.
     * Cambiar la declaración a 'public Actor update(Actor actor)' resulta en un error
     * ya que no coincide con la firma del método en la interfaz.
     * Este error se produce al intentar cambiar el tipo de retorno en la implementación.
     */

    @Override
    public void update(Actor actor) { // Intento de cambio de void a Actor en la implementación
        try (Connection connection = DBUtil.open(true)){
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)){
            actorDAO.delete(connection, id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Actor> findActorsByMovieId(int id) {
        try (Connection connection = DBUtil.open(true)){
            List<ActorEntity> actorsEntities = actorDAO.findActorsByMovieId(connection, id);
            return actorsEntities.stream()
                    .map(ActorMapper.mapper::toActor)
                    .toList();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
