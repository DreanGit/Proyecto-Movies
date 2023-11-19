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
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.model.ActorEntity;
import com.fpmislata.movies.persistence.model.DirectorEntity;

@Component
public class DirectorDAO {

    public Optional<DirectorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id)); // Se ejecuta la consulta
            return Optional.ofNullable(resultSet.next()? DirectorMapper.mapper.toDirectorEntity(resultSet):null); //ofNullable: Este método crea un Optional que contiene el valor proporcionado (el registro resultSet que mapearemos a directorEntity), el cual puede ser null. Si el valor es null, se creará un Optional vacío.
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
        
    public int insert(Connection connection, DirectorEntity directorEntity) { // el directorEntity lo he mapeado de director de servicio en DirectorRepositoryImpl
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>(); 
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear()); // datos que vienen del JSON del Postman
        params.add(directorEntity.getDeathYear());
        int id = DBUtil.insert(connection, SQL, params);
        return id; //  Devuelve el ID asignado al nuevo director que llegara hasta controller pasando por las capas
    }// como aqui no devuelvo un objeto no hace falta que convierta de resultSet a DirectorEntity

    public void update(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        params.add(directorEntity.getId());
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    public Optional<DirectorEntity> findDirectorByMovieId(Connection connection, int movieId) {
        final String SQL = """
            SELECT d.* FROM directors d 
            INNER JOIN  movies m ON m.director_id = d.id
            WHERE m.id = ?
            LIMIT 1
        """;
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            return Optional.ofNullable(resultSet.next()? DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<DirectorEntity> getAll(Connection connection) {
        String SQL = "SELECT * FROM directors";

        List<DirectorEntity> directorsEntity = new ArrayList<>(); // 1- creo una lista de directorsEntity
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                directorsEntity.add(DirectorMapper.mapper.toDirectorEntity(resultSet)); // 2- relleno la lista de resultados resultSet que mapeo a directorsEntity
            }
            return directorsEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e); // Es buena práctica proporcionar la excepción original como causa
        }
    }
}