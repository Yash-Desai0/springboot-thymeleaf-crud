package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.constant.APIConstant;
import com.codemechSolutions.crud.entity.Movie;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.request.MovieRequest;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(APIConstant.MOVIE_URL)
public class MovieController {

    @Autowired
    private MovieService movieService;;

    @PostMapping
    public ResponseEntity<ResultStatusResponse> saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return movieService.saveMovie(movieRequest);
    }

    @GetMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) throws ActorMoviePortalException {
        return movieService.getMovieById(id);
    }

    @PutMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<ResultStatusResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) throws ActorMoviePortalException {
        return movieService.updateMovie(id, movieRequest);
    }

    @DeleteMapping(APIConstant.MOVIE_ID)
    public ResponseEntity<ResultStatusResponse> deleteMovie(@PathVariable Long id) throws ActorMoviePortalException {
        return movieService.deleteMovieById(id);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() throws ActorMoviePortalException {
        return movieService.getAllMovies();
    }
}