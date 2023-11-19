// Definimos el paquete en el que se encuentra nuestra interfaz.
package com.fpmislata.movies.domain.repo;

// Importamos las clases que vamos a utilizar en nuestra interfaz.
import java.util.List; // List es una colección de elementos que permite duplicados y se mantiene en orden.
import java.util.Optional; // Optional es un contenedor que puede o no contener un valor no nulo.

import com.fpmislata.movies.domain.entity.Actor; // Importamos la clase Actor, que es nuestra entidad de dominio.

// Esta interfaz define las operaciones del repositorio para la entidad Actor.
public interface ActorRepository {

    // Método para insertar un nuevo actor en la base de datos. Devuelve un int, que suele ser el ID generado para el actor insertado.
    int insert(Actor actor);

    // Método para buscar un actor por su ID. Devuelve un Optional de Actor, lo que significa que puede o no encontrar un actor.
    Optional<Actor> find(int id);

    // Método para actualizar un actor existente. Originalmente devuelve void, pero lo cambiarás para que devuelva el Actor actualizado.
    void update(Actor actor); // Cambiado de void a Actor según tu solicitud.

    // Método para eliminar un actor por su ID. No devuelve nada (void) porque es una operación que o bien se completa o lanza una excepción.
    void delete(int id);

    // Método para buscar actores por el ID de una película. Devuelve una lista de actores que están asociados con la película especificada.
    List<Actor> findActorsByMovieId(int id);

    public List<Actor> getAll();
}
