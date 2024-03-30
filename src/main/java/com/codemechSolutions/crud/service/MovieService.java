package com.codemechSolutions.crud.service;

import com.codemechSolutions.crud.entity.Movie;
import com.codemechSolutions.crud.request.MovieRequest;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<Movie> getMovieById(Long id) throws ResourceNotFoundException;

    ResponseEntity<List<Movie>> getAllMovies() throws ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> saveMovie(Movie movie );

    ResponseEntity<ResultStatusResponse> updateMovie(Long id, MovieRequest movieRequest) throws ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> deleteMovieById(Long id) throws ResourceNotFoundException;

}
