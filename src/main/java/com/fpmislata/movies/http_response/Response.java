package com.fpmislata.movies.http_response;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Getter;
import lombok.Setter;

//La clase Response es una clase utilizada para formatear y enviar respuestas desde tu API al cliente en formato JSON

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  //PARA QUE NO SALGAN LOS NULOS EN EL JSON
@JsonPropertyOrder({ "page", "Page Sizee", "Total Pages", "Total Records", "previous", "next", "data"}) // Orden en que aparecen en el JSON
public class Response {

    private Object data;

    //Los ponemos como Integer para que el valor por defecto sea nulo y no 0, así se pueden excluir del JSON
    @JsonProperty("Total Records")
    private Integer totalRecords; // número total de resultados en la busqueda (num de películas)
    private Integer page;         // dato que recogeré de la URL con el @RequestParam
    @JsonProperty("Page Sizee")
    private Integer pageSize;     // el tamaño de la página que lo pondremos en 10 (variable LIMIT)
    @JsonProperty("Total Pages")
    private Integer totalPages;   // totalRecords / pageSize
    private String next;
    private String previous;

    //Crear constructor con unicamente el campo data.
    public Response (Object data){
        this.data = data;
    }
    
    public Response(Object data, int totalRecords, Optional<Integer> page, Optional<Integer> pageSize) {  
        this.data = data;
        this.totalRecords = totalRecords;
        if (page.isPresent()) {
            buildPaginationMetaData(totalRecords, pageSize.get(), page.get()); //Cuando un parámetro es de tipo Optional, debes usar .get() para obtener el valor envuelto en el Optional
        }
    }

    private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();

        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));  // 222/10 = 22,2 => 23,0 con .ceil => 23 con (int)
        this.totalPages = totalPages;

        if(page > 1 && totalPages > 1)
            this.previous = url + "?page=" + (page - 1);
        if(page < totalPages)
            this.next = url + "?page=" + (page + 1);
    }

}