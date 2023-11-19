/**
 * Clase ActorServiceImpl: Implementación del Servicio de Actores.
 *
 * Este archivo contiene la implementación de la interfaz ActorService, que define las operaciones
 * de negocio relacionadas con los actores en la aplicación. La clase sigue el patrón de servicio
 * para desacoplar la lógica de negocio del acceso a datos, que es manejado por los repositorios.
 *
 * Anotada con @Service, esta clase es gestionada por Spring, lo que permite su inyección automática
 * donde sea necesario en la aplicación.
 *
 * Proporciona métodos para:
 * - Insertar un nuevo actor en la base de datos.
 * - Actualizar la información de un actor existente.
 * - Eliminar un actor de la base de datos.
 * - Buscar un actor por su ID.
 *
 * Implementa manejo de errores lanzando ResourceNotFoundException cuando no se encuentra un actor requerido.
 */

package com.fpmislata.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.repo.ActorRepository;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.exception.ResourceNotFoundException;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;


    @Override
    public int create(Actor actor) {
        return actorRepository.insert(actor);
    }

    @Override
    public void update(Actor actor) {
        Actor existingActor = actorRepository.find(actor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actor.getId()));
        actorRepository.update(actor);
    }

    @Override
    public void delete(int id) {
        Actor actor = actorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        actorRepository.delete(id);
    }

    @Override
    public Actor find(int id) {
        Actor actor = actorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        return actor;
    }

    @Override
    public List<Actor> getAll() {
        return actorRepository.getAll();
    }
}
