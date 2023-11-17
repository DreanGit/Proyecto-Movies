package com.fpmislata.movies.bussines.service;

import com.fpmislata.movies.bussines.entity.Director;

public interface DirectorService {
    
    public int insert(Director director);

    public void update(Director director);

    public void delete(int id);
}
