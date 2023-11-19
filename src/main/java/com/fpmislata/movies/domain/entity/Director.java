package com.fpmislata.movies.domain.entity;

/**
 * La clase Director representa la entidad 'Director' en el dominio de la aplicación.
 * Modela los datos y el comportamiento de un director de películas en el sistema.
 * Incluye información básica como el nombre y los años de nacimiento y, si aplica, de defunción.
 *
 * Esta clase es un simple POJO (Plain Old Java Object) que incluye:
 * - Propiedades privadas para los datos.
 * - Constructores para crear instancias de la clase.
 * - Métodos 'getter' y 'setter' para acceder y modificar las propiedades.
 * - Un método toString() para representar la información del director en forma de String.
 */
public class Director {

    // Campos de la clase para almacenar los datos del director.
    private int id;            // Identificador único del director.
    private String name;       // Nombre del director.
    private int birthYear;     // Año de nacimiento del director.
    // deathYear es de tipo Integer para permitir valores nulos, es decir, cuando no se conoce la fecha de defunción o aún está vivo.
    private Integer deathYear;

    // Constructor sin argumentos requerido por Jackson para deserializar JSON en objetos de esta clase.
    public Director() {
 
    }

    // Constructor completo para inicializar todas las propiedades de la clase.
    public Director(int id, String name, int birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Constructor sobrecargado sin el campo 'id', útil para crear instancias cuando el 'id' es desconocido o será generado automáticamente.
    public Director(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Métodos 'getter' y 'setter' para cada propiedad.
    // Permiten obtener y establecer los valores de las propiedades de un director.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    // Método toString() sobreescrito para proporcionar una representación en String de un objeto Director.
    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }

    // Este método parece estar fuera de lugar, ya que duplica el comportamiento de Optional.orElse, pero no pertenece aquí.
    // Es recomendable eliminar este método para evitar confusión y seguir las mejores prácticas de diseño de clases.
    public Director orElse(Object object) {
        return null;
    }
}
