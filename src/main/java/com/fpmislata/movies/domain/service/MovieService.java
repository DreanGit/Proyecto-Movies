// Definimos el paquete al que pertenece nuestra interfaz, esto ayuda a organizar el código y evitar colisiones de nombres.
package com.fpmislata.movies.domain.service;

// Importamos las clases que vamos a usar en nuestra interfaz.
import java.util.List; // List es una colección que almacena elementos en un orden específico y permite duplicados.
import java.util.Optional; // Optional es un contenedor que puede o no contener un valor no nulo.

import com.fpmislata.movies.domain.entity.Movie; // Importamos la clase Movie que representa la entidad de una película.

// Definimos una interfaz, que es como un contrato que especifica qué métodos deben implementar las clases que la utilicen.
public interface MovieService {
    
    // Método para obtener todas las películas. Puede recibir valores opcionales para paginación.
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> pageSize);

    // Método para encontrar una película por su ID. Devuelve un solo objeto Movie.
    public Movie find(int id);

    // Método que devuelve el número total de películas registradas.
    public int getTotalNumberOfRecords();

    // Método para insertar una nueva película, recibiendo el objeto Movie y los IDs del director y actores asociados.
    public int insertMovie(Movie movie, int directorId, List<Integer> actorIds);
}
