package com.fpmislata.movies.domain.service;

import java.util.List;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorService {

    public int create(Director director);

    void update(Director director);

    void delete(int id);

    Director find(int id);

    List<Director> getAll();
    
}
