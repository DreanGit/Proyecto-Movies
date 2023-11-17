package com.fpmislata.movies.controller;

import com.fpmislata.movies.bussines.entity.Director;
import com.fpmislata.movies.bussines.entity.Actor;
import com.fpmislata.movies.bussines.service.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/actors")
@RestController


public class ActorController {
    @Autowired
    ActorService actorService;

    Actor actor = new Actor("Manolito", 1992,1990);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/insert")
    public void insert() {
        actorService.insertActor(actor);
    }

    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // @PutMapping("/{id}")
    // public void update(@PathVariable("id") int id,@RequestBody Actor actor){
    //     actor.setId(id);
    //     directorService.update(director);
    // }
}
