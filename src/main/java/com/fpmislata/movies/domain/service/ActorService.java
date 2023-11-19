package com.fpmislata.movies.domain.service;

import java.util.List;

import com.fpmislata.movies.domain.entity.Actor;


public interface ActorService {

    int create(Actor actor);

    void update(Actor actor); 

    void delete(int id);

    Actor find(int id);

    public List<Actor> getAll();
}