package com.codemechsolutions.crud.service;

import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<Movie> getMovieById(Long id) throws ResourceNotFoundException;

    ResponseEntity<List<Movie>> getAllMovies() throws ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> saveMovie(Movie movie );

    ResponseEntity<ResultStatusResponse> updateMovie(Long id, MovieRequest movieRequest) throws ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> deleteMovieById(Long id) throws ResourceNotFoundException;

}
