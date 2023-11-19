/**
 * Interfaz MovieRepository: Repositorio de Películas.
 * 
 * Esta interfaz declara los métodos esenciales para interactuar con la entidad Movie en la base de datos.
 * Actúa como un contrato que garantiza que cualquier implementación proporcionará los mecanismos necesarios
 * para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en lo que respecta a las películas.
 * 
 * Los métodos definidos son:
 * - getAll: Permite obtener una lista de todas las películas, con soporte para paginación a través de
 *   los parámetros 'page' y 'pageSize', haciendo que la carga de datos sea manejable y eficiente.
 * - find: Busca una película específica por su ID. Retorna un Optional<Movie>, lo que significa que
 *   puede o no encontrar una película, evitando así el manejo de valores nulos explícitos.
 * - getTotalNumberOfRecords: Proporciona el número total de registros de películas en la base de datos,
 *   lo cual es útil para operaciones que requieren conocer el tamaño del conjunto de datos.
 * - insert: Añade una nueva película a la base de datos y devuelve su ID generado, permitiendo una
 *   interacción inmediata con el nuevo registro.
 * 
 * El uso de esta interfaz facilita el desacoplamiento entre la lógica de negocio y la capa de acceso a datos,
 * permitiendo que la implementación específica del repositorio pueda variar sin afectar a las capas superiores.
 */


package com.fpmislata.movies.domain.repo;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {
    
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> pageSize);

    public Optional<Movie> find(int id);

    public int getTotalNumberOfRecords();

    public int insert(Movie movie);
}

