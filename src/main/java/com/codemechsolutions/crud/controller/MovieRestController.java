package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.constant.APIConstant;
import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.request.MovieRequest;
import com.codemechsolutions.crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(APIConstant.MOVIE_URL)
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ResultStatusResponse> saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setGenre(movieRequest.getGenre());
        movie.setActors(movie.getActors());
        return movieService.saveMovie(movie);
    }

    @GetMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) throws ResourceNotFoundException {
        return movieService.getMovieById(id);
    }

    @PutMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<ResultStatusResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) throws ResourceNotFoundException {
        return movieService.updateMovie(id, movieRequest);
    }

    @DeleteMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<ResultStatusResponse> deleteMovie(@PathVariable Long id) throws ResourceNotFoundException {
        return movieService.deleteMovieById(id);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() throws ResourceNotFoundException {
        return movieService.getAllMovies();
    }
}