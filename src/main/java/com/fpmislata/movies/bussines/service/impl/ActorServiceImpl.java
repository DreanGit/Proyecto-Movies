package com.fpmislata.movies.bussines.service.impl;

import com.fpmislata.movies.bussines.entity.Director;
import com.fpmislata.movies.bussines.repo.ActorRepository;
import com.fpmislata.movies.bussines.entity.Actor;
import com.fpmislata.movies.bussines.service.ActorService;
import com.fpmislata.movies.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    ActorRepository actorRepository;
        @Override
        public void insertActor(Actor actor) {
            actorRepository.insertActor(actor);
        }
        @Override
        public void update(Actor actor) {
            
        }
}
