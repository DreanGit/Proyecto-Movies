/**
 * Interfaz DirectorRepository: Repositorio de Directores.
 * 
 * Esta interfaz define las operaciones de acceso a datos para la entidad Director. Se sigue el patrón de diseño
 * de repositorio para abstraer la lógica de acceso a datos de la base de datos, permitiendo una mayor flexibilidad
 * y mantenibilidad al interactuar con la capa de persistencia.
 * 
 * Los métodos de esta interfaz permiten:
 * - Insertar un nuevo director en la base de datos.
 * - Buscar un director por su ID único.
 * - Actualizar los datos de un director existente.
 * - Eliminar un director de la base de datos usando su ID.
 * - Buscar un director asociado a una película específica por el ID de la película.
 * 
 * La utilización de Optional en los métodos de búsqueda es una práctica recomendada que permite manejar
 * de manera elegante los casos donde un director puede no estar presente, evitando así el uso de null y
 * reduciendo la posibilidad de excepciones de tipo NullPointerException.
 */


package com.fpmislata.movies.domain.repo;

import java.util.List;
import java.util.Optional;


import com.fpmislata.movies.domain.entity.Director;

public interface DirectorRepository {

    int insert(Director director);

    Optional<Director> find(int id);

    void update(Director director);

    void delete(int id);

    Optional<Director> findDirectorByMovieId(int id);

    public List<Director> getAll();
}
