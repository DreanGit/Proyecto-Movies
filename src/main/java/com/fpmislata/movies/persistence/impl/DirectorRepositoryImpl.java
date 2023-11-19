package com.fpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.repo.DirectorRepository;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;
import com.fpmislata.movies.persistence.model.DirectorEntity;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public List<Director> getAll() {  // estos métodos son los que implementa la interface Repository que ahora esta en domain

        try (Connection connection = DBUtil.open(true)){

            // Asegúrate de que actorDAO.getAll() no requiere parámetros y devuelve todos los actores.
            List<DirectorEntity> directorEntities = directorDAO.getAll(connection);  // 3- obtengo la lista de actorEntities del DAO y luego la mapeo a Actor 
            List<Director> director = directorEntities.stream()
                        .map(directorEntity -> DirectorMapper.mapper.toDirector(directorEntity))
                        .collect(Collectors.toList()); // Usa collect(Collectors.toList()) en lugar de .toList() si no estás en Java 16 o superior.

            return director;

        } catch (SQLException e) {
            throw new RuntimeException(e); // Es buena práctica pasar la excepción original como causa.
        }
    }

    @Override
    public Optional<Director> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<DirectorEntity> directorEntity = directorDAO.find(connection, id); //llamo al DAO, que encontrara un resultSet que mapeara a directorEntity y nos lo devuelve, ese directorEntity ahora tengo que mapearlo para mandarlo a servicio 
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get())); //ese directorEntity que viene de DAO ahora tengo que mapearlo a director para mandarlo a servicio  
        } catch (SQLException e) {                                                     // si directorEntity no es nulo, toma el valor dentro de ese Optional (usando get()) y mapea ese valor a un Director     
            throw new RuntimeException();       
        }
    }
    /*En el contexto de un Optional, el método get() se utiliza para obtener el valor encapsulado por el Optional. Debes asegurarte de que el Optional no esté vacío antes de llamar a get(). Esto se puede hacer verificando con isPresent() o utilizando métodos más seguros como orElse() o isEmpty */

    @Override
    public int insert(Director director) {
        try (Connection connection = DBUtil.open(true)){
            DirectorEntity directorEntity = DirectorMapper.mapper.toDirectorEntity(director);// convierto de director de servicio a directorEntity de persistencia para usar en DAO
            return directorDAO.insert(connection, directorEntity); // lo inserto y me devuelve un id
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Director director) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.update(connection, DirectorMapper.mapper.toDirectorEntity(director));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public Optional<Director> findDirectorByMovieId(int id) {
        try(Connection connection = DBUtil.open(true)) {
            Optional<DirectorEntity> directorEntity = directorDAO.findDirectorByMovieId(connection, id);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get())); // lo mapeo para servicio que los inserte en movie en MovieService
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

