package com.fpmislata.movies.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.mapper.DirectorMapper;


@RequestMapping("/directors")
@RestController
public class DirectorController {
 
    @Autowired
    DirectorService directorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id){

        Director director = directorService.find(id);  // el find() de servicio devuelve un director que vamos a mapear
        return new Response(DirectorMapper.mapper.toDirectorDetailWeb(director));  // mapeo el director de service a directorDetailWeb de controlador
    }
     
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = directorService.create(DirectorMapper.mapper.toDirector(directorCreateWeb)); // igual que hacía antes llamo al create de servicio pero ahora le paso el mapeo de directorCreateWeb a DirectorMapper para la capa de servicio, y devuelve el ID del nuevo director creado (como antes, mirar abajo)
        DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
            id,
            directorCreateWeb.getName(),                    // aqui voy preparando el objeto que quiero devolver en el return
            directorCreateWeb.getBirthYear(),  
            directorCreateWeb.getDeathYear()
        );                                   // estos datos los recoge directorCreateWeb del postman, los recoge porque lo he puesto en Reques body. Y muestra el detalle de ese director creado
        return new Response(directorDetailWeb);           
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb) {
        directorUpdateWeb.setId(id);
        directorService.update(DirectorMapper.mapper.toDirector(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }

    // Método para obtener todos los directores.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll() {
        List<Director> director = directorService.getAll(); // Obtiene todos los actores sin paginación
        List<DirectorListWeb> directorListWEB = director.stream()
                .map(DirectorMapper.mapper::toDirectorListWeb) // Uso directo del mapper como en MovieController
                .collect(Collectors.toList()); // Realiza el mapeo a ActorListWeb
    
        // Utiliza el constructor que solo necesita la lista de actores para la propiedad 'data'.
        return new Response(directorListWEB);
    }
}