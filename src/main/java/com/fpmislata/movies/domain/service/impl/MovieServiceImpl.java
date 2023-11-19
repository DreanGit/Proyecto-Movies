/**
 * Clase MovieServiceImpl: Implementación del Servicio de Películas.
 * 
 * Este archivo contiene la implementación de la interfaz MovieService, definida para abstraer las operaciones
 * de negocio relacionadas con las películas en nuestra aplicación. Utiliza el patrón de diseño de servicio
 * para separar la lógica de negocio de las operaciones de acceso a datos, las cuales son manejadas por los repositorios.
 * 
 * La clase está marcada con la anotación @Service para que sea automáticamente detectada e instanciada por Spring
 * como un bean, lo cual permite su inyección en otras partes de la aplicación.
 * 
 * Los métodos definidos en esta clase permiten realizar las siguientes acciones:
 * - Obtener todas las películas, opcionalmente con paginación.
 * - Buscar una película específica por su ID y cargar su información relacionada.
 * - Obtener el total de registros de películas existentes.
 * - Insertar una nueva película en la base de datos junto con su director y lista de actores.
 * 
 * La clase maneja las excepciones lanzando ResourceNotFoundException cuando un recurso específico no se encuentra,
 * proporcionando así una gestión de errores coherente y desacoplada de la capa de persistencia.
 */



package com.fpmislata.movies.domain.service.impl;

// Importamos las clases de las entidades y los repositorios para poder usarlos.
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repo.ActorRepository;
import com.fpmislata.movies.domain.repo.DirectorRepository;
import com.fpmislata.movies.domain.repo.MovieRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;

/**
 * Esta clase es un "Service" en Spring, lo que significa que es una clase especializada en la lógica de negocio.
 * Aquí es donde definimos cómo queremos que se comporten nuestros servicios y cómo interactúan con las bases de datos a través de los repositorios.
 */
@Service
public class MovieServiceImpl implements MovieService {
    
    // Spring inyectará automáticamente la instancia correcta de MovieRepository aquí.
    // Esto nos permite no preocuparnos por cómo se crea o se obtiene esta instancia, Spring lo maneja por nosotros.
    @Autowired
    private MovieRepository movieRepository;

    // Lo mismo ocurre con ActorRepository y DirectorRepository.
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;
    
    /**
     * Método para obtener todas las películas.
     * @param page Número de página para la paginación de resultados.
     * @param pageSize Cantidad de películas por página.
     * @return Una lista de películas, posiblemente paginada.
     */
    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> pageSize) {
        // Llamamos al método correspondiente del repositorio.
        // La paginación es opcional, si no se proveen estos valores, se obtendrán todos los registros.
        return movieRepository.getAll(page, pageSize);
    }
    
    /**
     * Busca una película por su ID y carga también su director y actores asociados.
     * @param id El ID de la película que queremos buscar.
     * @return Un objeto Movie completo con su director y actores.
     */
    @Override
    public Movie find(int id) {
        // Buscamos la película en el repositorio por su ID. Si no se encuentra, lanzamos una excepción.
        Movie movie = movieRepository.find(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        // Buscamos el director asociado a la película. Si no hay director, se asignará null.
        Director director = directorRepository.findDirectorByMovieId(id).orElse(null);
    
        // Buscamos todos los actores asociados a esta película y los recogemos en una lista.
        List<Actor> actors = actorRepository.findActorsByMovieId(id);

        // Establecemos el director y los actores en el objeto de película.
        movie.setDirector(director);
        movie.setActors(actors);
 
        // Devolvemos la película con toda su información cargada.
        return movie;
    }

    /**
     * Calcula el total de registros de películas en la base de datos.
     * @return El número total de películas.
     */
    @Override
    public int getTotalNumberOfRecords() {
        // Simplemente devuelve el número total de películas que se obtiene del repositorio.
        return movieRepository.getTotalNumberOfRecords();
    }

    /**
     * Inserta una nueva película en la base de datos.
     * @param movie El objeto Movie con la información de la película a insertar.
     * @param directorId El ID del director de la película.
     * @param actorIds Una lista de IDs de actores que participan en la película.
     * @return El ID de la película insertada.
     */
    @Override
    public int insertMovie(Movie movie, int directorId, List<Integer> actorIds) {
        // Busca al director usando su ID, y si no lo encuentra, lanza una excepción.
        Director director = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));

        // Convierte los IDs de actores en objetos Actor, lanzando una excepción si alguno no se encuentra.
        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId)))
                .collect(Collectors.toList());

        // Establece el director y los actores en el objeto de la película.
        movie.setDirector(director);
        movie.setActors(actors);

        // Llama al método de inserción del repositorio y devuelve el ID de la película insertada.
        return movieRepository.insert(movie);
    }
}
