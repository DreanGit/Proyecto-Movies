package com.fpmislata.movies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.bussines.entity.Movie;
import com.fpmislata.movies.controller.model.movie.MovieDetailsWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;

// @Mapper(componentModel = "spring")
// public interface MovieMapper {
 
//     MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
 
//     MovieListWeb toMovieListWeb(Movie movie);
//     MovieDetailsWeb toMovieDetailsWeb(Movie movie);

    
// } CESAR

// @Mapper
// public interface MovieMapper {

//     @Mapping(source = "director", target = "directorListWeb")
//     @Mapping(source = "actors", target = "actorListWeb")
//     @Mapping(target = "runtime", ignore = true) // Si no quieres mapear runtime
//     MovieDetailsWeb toMovieDetailWeb(Movie movie);

//     DirectorListWeb mapDirectorToDirectorListWeb(Director director);

//     ActorListWeb mapActorToActorListWeb(Actor actor);
// } CHATGPT
