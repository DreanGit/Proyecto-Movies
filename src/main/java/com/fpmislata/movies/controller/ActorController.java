package com.fpmislata.movies.controller;

// Importamos las anotaciones y clases necesarias de Spring y del proyecto.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.fpmislata.movies.controller.model.actor.*;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.mapper.MovieMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * La clase ActorController es un controlador REST en Spring Boot.
 */

@RequestMapping("/actors") //RequestMapping: indica que todas las rutas de los métodos en esta clase estarán prefijadas con "/actors".
@RestController //@RestController: indica que los métodos devuelven datos directamente en formato JSON o XML.
public class ActorController {

    // Inyectamos el servicio que maneja la lógica de negocio para los actores.
    @Autowired
    ActorService actorService;

    // Método para obtener todos los actores.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll() {
        List<Actor> actors = actorService.getAll(); // Obtiene todos los actores sin paginación
        List<ActorListWeb> actorListWEB = actors.stream()
                .map(ActorMapper.mapper::toActorListWeb) // Uso directo del mapper como en MovieController
                .collect(Collectors.toList()); // Realiza el mapeo a ActorListWeb
    
        // Utiliza el constructor que solo necesita la lista de actores para la propiedad 'data'.
        return new Response(actorListWEB);
    }
    
    //Devuelve una respuesta HTTP con el detalle del actor.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id){
        // Usamos el mapper para convertir la entidad Actor a su DTO correspondiente y lo envolvemos en un objeto Response.
        return new Response (ActorMapper.mapper.toActorDetailWeb(actorService.find(id)));
    }

    // Método para crear un nuevo actor. Devuelve una respuesta HTTP con el detalle del actor creado.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ActorCreateWeb actorCreateWeb){
        // Creamos el actor a partir del DTO, obtenemos el ID generado y construimos el DTO de respuesta.
        int id = actorService.create(ActorMapper.mapper.toActor(actorCreateWeb));
        ActorDetailWeb actorDetailWeb = new ActorDetailWeb(
            id,
            actorCreateWeb.getName(),
            actorCreateWeb.getBirthYear(),
            actorCreateWeb.getDeathYear()
        );
        return new Response(actorDetailWeb);
    }

    // Método para actualizar un actor existente. No devuelve contenido en la respuesta HTTP.
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody ActorUpdateWeb actorUpdateWeb) {
        // Establecemos el ID en el DTO y luego actualizamos el actor mediante el servicio.
        actorUpdateWeb.setId(id);
        actorService.update(ActorMapper.mapper.toActor(actorUpdateWeb));
    }

    // Método para eliminar un actor por ID. No devuelve contenido en la respuesta HTTP.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
       actorService.delete(id);
    }
    

}
