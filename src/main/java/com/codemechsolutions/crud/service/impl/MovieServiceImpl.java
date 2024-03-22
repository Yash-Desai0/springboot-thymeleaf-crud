package com.codemechsolutions.crud.service.impl;

import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.repository.MovieRepository;
import com.codemechsolutions.crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(long id) throws ResourceNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found for this id :: "+id));
    }

    @Override
    public void deleteMovieById(long id) throws ResourceNotFoundException {
        movieRepository.delete(movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found for this id :: "+id)));
    }

    @Override
    public boolean getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
}
