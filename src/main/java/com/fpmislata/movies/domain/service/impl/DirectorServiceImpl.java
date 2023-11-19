/**
 * Clase DirectorServiceImpl: Implementación del Servicio de Directores.
 * 
 * Este archivo proporciona la implementación concreta de la interfaz DirectorService. Define los métodos
 * para las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para directores en la aplicación.
 * Está anotado con @Service para que Spring gestione su ciclo de vida y pueda ser inyectado donde sea necesario.
 * 
 * Los métodos cubren:
 * - Buscar un director por ID.
 * - Crear (insertar) un nuevo director en la base de datos.
 * - Actualizar la información de un director existente.
 * - Eliminar un director de la base de datos por ID.
 * 
 * Cada operación maneja la posibilidad de que el director no exista, arrojando una excepción personalizada.
 */

package com.fpmislata.movies.domain.service.impl;

import java.util.List;
// Importaciones requeridas para el funcionamiento del servicio.
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.repo.DirectorRepository;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.exception.ResourceNotFoundException;

@Service
public class DirectorServiceImpl implements DirectorService {

    // Inyectamos el repositorio para poder interactuar con la base de datos.
    @Autowired
    private DirectorRepository directorRepository;

    /**
     * Busca un director en la base de datos por su ID.
     * @param id El ID del director a buscar.
     * @return El director encontrado.
     */
    @Override
    public Director find(int id) {
        // Utiliza el método 'find' del repositorio, si no encuentra al director, arroja una excepción.
        return directorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
    }

    /**
     * Crea (inserta) un nuevo director en la base de datos.
     * @param director El objeto Director con la información a insertar.
     * @return El ID del director creado.
     */
    @Override
    public int create(Director director) {
        // Llama al método 'insert' del repositorio y devuelve el ID del director insertado.
        return directorRepository.insert(director);
    }

    /**
     * Actualiza la información de un director existente en la base de datos.
     * @param director El objeto Director con la información actualizada.
     */
    @Override
    public void update(Director director) {
        // Comprueba si el director existe, si no, arroja una excepción.
        Director existingDirector = directorRepository.find(director.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + director.getId()));
        // Actualiza la información del director en la base de datos.
        directorRepository.update(director);
    }

    /**
     * Elimina un director de la base de datos por su ID.
     * @param id El ID del director a eliminar.
     */
    @Override
    public void delete(int id) {
        // Comprueba si el director existe, si no, arroja una excepción.
        Director director = directorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
        // Elimina al director de la base de datos.
        directorRepository.delete(id);
    }

    @Override
    public List<Director> getAll() {
        return directorRepository.getAll();
    }
}
