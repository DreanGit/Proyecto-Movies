package com.fpmislata.movies.controller;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.fpmislata.movies.bussines.entity.Movie;
import com.fpmislata.movies.bussines.service.MovieService;
import com.fpmislata.movies.http_response.Response;

import java.util.List;
import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

        @Autowired
        MovieService moviesService;
        private final int LIMIT=10;
        
        @ResponseStatus(HttpStatus.OK)
        @GetMapping("")
        public Response findMovies(@RequestParam Optional<Integer> page) {

                Response response=new Response(moviesService.findMovies(page),moviesService.getTotalRecords(), page,LIMIT);
                return response;
                

        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/{id}")
        public Movie find(@PathVariable("id") int id) {

                return moviesService.findMoviesById(id);
        }

}