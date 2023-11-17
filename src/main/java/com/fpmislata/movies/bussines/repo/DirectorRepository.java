package com.fpmislata.movies.bussines.repo;

import com.fpmislata.movies.bussines.entity.Director;

public interface DirectorRepository {

    public int insert(Director director);
    
    public void update(Director director);

    public Director findDirector(int id);

    public void delete(int id);
}
