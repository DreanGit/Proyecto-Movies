package com.fpmislata.movies.bussines.repo;

import com.fpmislata.movies.bussines.entity.Actor;

import java.util.List;

public interface ActorRepository {

    public void insertActor(Actor actor);

    public void update(Actor actor);

    public Actor findActor(int id);
    
}
