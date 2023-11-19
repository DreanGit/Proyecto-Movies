package com.fpmislata.movies.domain.entity;

/**
 * La clase Actor representa a un actor en el dominio de la aplicación.
 * Contiene información sobre el actor, como su identificador, nombre y años de nacimiento y defunción.
 * La propiedad deathYear es de tipo Integer para permitir valores nulos, lo que indica que un actor puede estar vivo.
 * 
 * Esta clase es utilizada por la capa de persistencia para representar actores en la base de datos,
 * así como por los servicios y controladores para procesar y responder a las solicitudes de la API.
 */
public class Actor {

    // Propiedades privadas que representan los atributos de un actor.
    private int id;            // El identificador único para el actor.
    private String name;       // El nombre del actor.
    private int birthYear;     // El año de nacimiento del actor.
    private Integer deathYear; // El año de defunción del actor, puede ser nulo si el actor está vivo.

    // Constructor vacío necesario para ciertas operaciones de serialización/deserialización, por ejemplo, por la librería Jackson.
    public Actor(){

    }

    // Constructor completo para crear una instancia de Actor con todas sus propiedades.
    public Actor(int id, String name, int birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Constructor sobrecargado sin el identificador, útil cuando el identificador es generado automáticamente (por ejemplo, en una base de datos).
    public Actor(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Métodos 'getter' y 'setter' para acceder y modificar las propiedades de Actor.
    // Estos métodos son estándares en JavaBeans y se usan comúnmente en varias librerías y frameworks.
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

    // Método toString sobreescrito para proporcionar una representación en cadena de caracteres de la instancia de Actor.
    // Es útil para depurar, registrar, o simplemente ver la información del Actor en un formato legible.
    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
