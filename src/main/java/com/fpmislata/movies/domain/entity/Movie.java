package com.fpmislata.movies.domain.entity;

import java.util.List;

/**
 * La clase Movie representa la entidad de una película en el dominio de nuestra aplicación.
 * Contiene información básica como el título, año, duración y las relaciones con el director y los actores.
 */
public class Movie {
    // Campos privados de la clase Movie, representan los atributos de una película.
    private int id; // Identificador único de la película.
    private String title; // Título de la película.
    private int year; // Año de lanzamiento de la película.
    private int runtime; // Duración de la película en minutos.
    private Director director; // Referencia al director de la película.
    private List<Actor> actors; // Lista de actores que participan en la película.

    // Constructor por defecto.
    public Movie() {
    }
 
    // Constructor con parámetros para crear una instancia con valores iniciales.
    public Movie(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
 
    // Constructor sin ID, útil para crear instancias antes de persistirlas en la base de datos.
    public Movie(String title, int year, int runtime) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
 
    // Métodos getter y setter para cada campo.
    // Los getters permiten obtener el valor de los campos, y los setters permiten modificarlos.
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public int getYear() {
        return year;
    }
 
    public void setYear(int year) {
        this.year = year;
    }
 
    public int getRuntime() {
        return runtime;
    }
 
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
 
    public Director getDirector() {
        return director;
    }
 
    public void setDirector(Director director) {
        this.director = director;
    }
 
    public List<Actor> getActors() {
        return actors;
    }
 
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
 
    // Método toString que proporciona una representación en forma de String de la instancia de Movie.
    // Esto es particularmente útil para depuración y registros de logs.
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", director=" + (director != null ? director.toString() : "null") +
                ", actors=" + actors +
                '}';
    }
}
