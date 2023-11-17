package com.fpmislata.movies.bussines.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.bussines.entity.Director;
import com.fpmislata.movies.bussines.repo.DirectorRepository;
import com.fpmislata.movies.bussines.service.DirectorService;
import com.fpmislata.movies.exceptions.ResourceNotFoundException;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int insert(Director director) { //cambiar insert por create(?)
        return directorRepository.insert(director);
    }

    @Override
    public void update(Director director) {
        Director existingDirector = directorRepository.findDirector(director.getId());
        if (existingDirector == null) {
            throw new ResourceNotFoundException("Director not found with id: " + director.getId());
        }
        
        directorRepository.update(director);
    }

    public void delete(int id) {
        Director director = directorRepository.findDirector(id);
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        directorRepository.delete(id);
    }
    
}