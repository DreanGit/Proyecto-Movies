package com.fpmislata.movies.mapper;

// Importaciones necesarias para el mapeo y el manejo de excepciones SQL.
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// Importaciones de los modelos de datos usados en la capa de controlador y en la capa de persistencia.
import com.fpmislata.movies.controller.model.director.*;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.model.DirectorEntity;

/**
 * Interfaz de mapeo para la entidad Director.
 *
 * Errores conocidos y sus soluciones:
 * 
 * Error 1: "No implementation was created for DirectorMapper"
 * - Este error sugiere que hay un conflicto con otro procesador de anotaciones que debería procesar
 *   'java.util.ArrayList' o similar.
 * - Para resolverlo, asegúrate de que no hay conflictos con otros procesadores de anotaciones y que
 *   todas las clases y tipos utilizados están disponibles y correctamente anotados.
 * - Puedes habilitar el modo verboso de MapStruct agregando el argumento de compilación 
 *   '-Amapstruct.verbose=true' para obtener más detalles del error.
 *
 * Error 2: "Duplicate method toDirector(DirectorCreateWeb)"
 * - Este error ocurre cuando hay métodos con firmas idénticas declaradas en la interfaz.
 * - Revisa la interfaz para asegurarte de que no hay duplicados en las definiciones de los métodos.
 * - Si usas herencia de interfaces, verifica que no haya métodos con la misma firma en las interfaces padre.
 */
@Mapper(componentModel = "spring") // Anotación para integrar el mapeador con Spring.
public interface DirectorMapper {
 
    // Instancia del mapeador que puede ser utilizada en toda la aplicación.
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);
 
    // Define el mapeo de la entidad Director al DTO para mostrar detalles.
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    
    // Define el mapeo de la entidad Director al DTO para listar directores.
    DirectorListWeb toDirectorListWeb(Director director);

    // Define el mapeo del DTO de creación al objeto Director.
    // Si tienes el error "Duplicate method...", asegúrate de que este método no esté declarado más de una vez.
    Director toDirector(DirectorCreateWeb directorCreateWeb);
    
    // Define el mapeo del DTO de actualización al objeto Director.
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);

    // Define el mapeo de la entidad Director a la entidad de persistencia DirectorEntity.
    DirectorEntity toDirectorEntity(Director director);
    
    // Define el mapeo de la entidad de persistencia DirectorEntity a la entidad Director.
    Director toDirector(DirectorEntity DirectorEntity);
     
    // Mapeo especial para convertir un ResultSet en una entidad DirectorEntity.
    // Asegúrate de que los nombres de las columnas coincidan con los de tu base de datos.
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null) ? resultSet.getInt(\"deathYear\"):null)")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;

}
