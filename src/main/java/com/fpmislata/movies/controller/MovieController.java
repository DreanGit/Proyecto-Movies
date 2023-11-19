/**
 * Clase controladora de películas que gestiona las solicitudes HTTP relacionadas con películas.
 * Provee endpoints para realizar operaciones CRUD en la entidad Movie a través de la API REST.
 * Utiliza MovieService para la lógica de negocio y MovieMapper para mapear entre entidades de dominio y DTOs.
 *
 * La anotación @RequestMapping("/movies") indica que todos los endpoints de esta clase tendrán como base la ruta '/movies'.
 * La anotación @RestController indica que esta clase es un controlador REST, y sus métodos devuelven datos directamente en lugar de vistas o plantillas.
 *
 * Los métodos incluyen:
 * - getAll: para obtener todas las películas con paginación opcional.
 * - find: para buscar una película específica por su ID.
 * - createMovie: para crear una nueva película a partir de datos proporcionados en formato JSON.
 *
 * La clase también muestra el uso de @Value para inyectar valores desde el archivo de propiedades,
 * permitiendo configurar el límite de tamaño de página para la paginación.
 *
 * Antes de la introducción de mappers, los métodos interactuaban directamente con las entidades de dominio.
 * Con los mappers, ahora se devuelven DTOs que proporcionan una capa de abstracción entre la representación interna de los datos y
 * lo que se expone a través de la API, mejorando la seguridad y la mantenibilidad del código.
 */

package com.fpmislata.movies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.MovieMapper;


@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Value("${LIMIT}")
    private Integer LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {

        int totalRecords = movieService.getTotalNumberOfRecords();

        if (pageSize.isEmpty()){
            pageSize = Optional.of(LIMIT);
        }

        List<Movie> movies =movieService.getAll(page,pageSize);     // 4- me traig de servicio la lista de movies y la convierto a movieListWeb
        List<MovieListWeb> movieListWEB = movies.stream()                  
                .map(movie -> MovieMapper.mapper.toMovieListWEB(movie))
                .toList();                                               // realizo el mapeo del movie de servicio a toMovieListWEB de controlador

        Response response = new Response(movieListWEB,totalRecords, page, pageSize);
        //Response response = new Response(movieService.getAll(page, pageSize), totalRecords, page, pageSize); // este constructor si no tengo ni page ni pageSize no ejecutará el buildPaginationMetaData y solo devuelve data (que es el movieService.getAll(page, pageSize) y que al estar vacío en persistencia solo devuelve la lista y no completa la sentencia sql) y totalRecords 
        
        return response;   
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        Movie movie = movieService.find(id);
        return new Response (MovieMapper.mapper.toMovieDetailWEB(movie)); // realizo el mapeo del movie de service a toMovieDetailWEB de controlador

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
     public Response createMovie(@RequestBody MovieCreateWeb movieCreateWeb){ 
        
        int id = movieService.insertMovie(                    // 1. Llamada al servicio para insertar la película con director y actores
                MovieMapper.mapper.toMovie(movieCreateWeb),   // en insert del servicio: public int insertMovie(Movie movie, int directorId, List<Integer> actorIds)
                movieCreateWeb.getDirectorId(),
                movieCreateWeb.getActorIds()                  //recordar que movieCreateWeb tiene los int de directorId y actorIds
        );
        MovieListWeb movieListWeb = new MovieListWeb();      // 2. Creación de un objeto MovieListWeb para devolver en la respuesta
        movieListWeb.setTitle(movieCreateWeb.getTitle());    //2.1 le meto el título de la película recien creada (del objeto MovieCreateWeb)
        movieListWeb.setId(id);                              //2.1 le meto el ID de la película generado por la inserción

        return new Response(movieListWeb); // para simplificar esta vez devolvemos la listWeb que tiene id y titulo
    }
}









