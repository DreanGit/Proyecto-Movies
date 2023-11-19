package com.fpmislata.movies.controller.model.director;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //Genera un constructor sin argumentos.
@AllArgsConstructor //Genera un constructor que acepta todos los campos de la clase como argumentos.
@JsonInclude(JsonInclude.Include.NON_NULL) // si en el mapeador de resultSet a ModelEntity ya trato esto hace falta ponerlo aquí? SSSSIIIII HACE FALTA
public class DirectorDetailWeb {
 
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
 
}


