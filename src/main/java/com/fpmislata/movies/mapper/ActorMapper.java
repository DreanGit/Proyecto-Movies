package com.fpmislata.movies.mapper;

// Importaciones necesarias de Java y MapStruct.
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.fpmislata.movies.controller.model.actor.*;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.persistence.model.ActorEntity;

/**
 * La interfaz ActorMapper utiliza MapStruct para definir mapeos entre diferentes tipos de objetos relacionados con la entidad Actor.
 * MapStruct es un generador de código que simplifica las conversiones entre diferentes tipos de objetos Java, basado en una convención sobre configuración.
 * El mapeo se realiza en tiempo de compilación, lo que significa que no hay impacto en el rendimiento en tiempo de ejecución.
 */
@Mapper(componentModel = "spring") // Indica que MapStruct debe generar un componente Spring.
public interface ActorMapper {

    // Instancia del mapper que puede ser utilizada donde sea necesario.
    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);
    
    // Métodos de mapeo que definen cómo convertir entre entidades y DTOs.
    ActorDetailWeb toActorDetailWeb(Actor actor);
    ActorListWeb toActorListWeb(Actor actor);
 
    // Métodos para convertir desde los DTOs de creación y actualización a la entidad Actor.
    Actor toActor(ActorCreateWeb ActorCreateWeb);
    Actor toActor(ActorUpdateWeb ActorUpdateWeb);

    // Métodos para convertir entre la entidad de persistencia ActorEntity y la entidad de dominio Actor.
    ActorEntity toActorEntity(Actor actor);
    Actor toActor(ActorEntity actorEntity);

    // Método de mapeo especial que convierte un ResultSet de SQL a una entidad de persistencia ActorEntity.
    // Esto es útil para trabajar directamente con los resultados de consultas de bases de datos.
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null) ? resultSet.getInt(\"deathYear\"):null)")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;


}
