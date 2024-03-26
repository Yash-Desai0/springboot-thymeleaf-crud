package com.codemechsolutions.crud.service;

import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
    Movie getMovieById(long id) throws ResourceNotFoundException;
    void deleteMovieById(long id) throws ResourceNotFoundException;
    boolean getMovieByTitle(String title) ;

}
