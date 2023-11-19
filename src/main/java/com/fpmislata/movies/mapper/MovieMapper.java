package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.persistence.model.MovieEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Interfaz MovieMapper para mapear objetos relacionados con la entidad Movie en diferentes capas de la aplicación.
 * Utiliza la biblioteca MapStruct para generar implementaciones en tiempo de compilación, lo que mejora el rendimiento en tiempo de ejecución.
 * La anotación @Mapper asegura la integración con el contenedor Spring y permite la inyección de la implementación generada.
 */
@Mapper(componentModel = "spring")  
public interface MovieMapper {

    // Instancia del mapeador que puede ser utilizada en la aplicación.
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    // Método para convertir la entidad Movie a MovieListWeb, utilizado para listados de películas.
    MovieListWeb toMovieListWEB(Movie movie);      

    // Método para convertir la entidad Movie a MovieDetailWeb, utilizado para mostrar detalles de una película.
    MovieDetailWeb toMovieDetailWEB(Movie movie);  

    // Método para convertir un ResultSet SQL en la entidad de persistencia MovieEntity.
    // Este mapeo es útil para trabajar con resultados de consultas SQL directamente.
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    // Método para convertir la entidad de persistencia MovieEntity a la entidad de dominio Movie.
    // Nota: Asegúrate de que la entidad Movie tenga un constructor vacío para evitar errores de compilación.
    Movie toMovie(MovieEntity movieEntity);

    // Método para convertir el DTO MovieCreateWeb en la entidad Movie, ignorando los campos que no corresponden directamente.
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    // Método para convertir la entidad Movie a la entidad de persistencia MovieEntity, incluyendo la conversión de relaciones complejas.
    @Mapping(target = "directorId", expression = "java(movie.getDirector().getId())")
    @Mapping(target = "actorIds", expression = "java(mapActorsToActorIds(movie.getActors()))")
    MovieEntity toMovieEntity(Movie movie);

    // Método auxiliar para convertir una lista de entidades Actor a una lista de sus identificadores.
    // Útil para mapear relaciones uno a muchos.
    @Named("actorToActorIds")
    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(Actor::getId)
                .toList();
    }
}
