package com.codemechsolutions.crud.service.impl;

import com.codemechsolutions.crud.domain.ResultStatus;
import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.repository.MovieRepository;
import com.codemechsolutions.crud.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codemechSolutions.crud.constant.APIConstant.CLS_MET_ERROR;
import static com.codemechSolutions.crud.constant.APIConstant.MET_UPDATE_MOVIE_BY_ID;
import static com.codemechSolutions.crud.constant.APIConstant.MET_GET_MOVIE_BY_ID;
import static com.codemechSolutions.crud.constant.APIConstant.MET_GET_ALL_MOVIES;
import static com.codemechSolutions.crud.constant.APIConstant.MET_SAVE_MOVIE;
import static com.codemechSolutions.crud.constant.APIConstant.MET_DELETE_MOVIE_BY_ID;

import static com.codemechsolutions.crud.constant.APIConstant.CLS_MET_ERROR;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

     private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    private Movie  getById(Long id) throws ResourceNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("movie not found by given id :: "+id));
    }

    @Override
    public ResponseEntity<Movie> getMovieById(Long id) throws ResourceNotFoundException {
        try
        {
            return new ResponseEntity<>(getById(id), HttpStatus.OK);
        }catch (ActorMoviePortalException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_GET_MOVIE_BY_ID, e.getMessage());
            throw new ActorMoviePortalException("Unable to get Movie. Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<Movie>> getAllMovies() throws ActorMoviePortalException {
        try {
            return new ResponseEntity<>(movieRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_GET_ALL_MOVIES, e.getMessage());
            throw new ActorMoviePortalException("Unable to get Movies. Try again later.");
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> saveMovie(Movie movie){
        try{
            movieRepository.save(movie);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_SAVE_MOVIE, e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<ResultStatusResponse> updateMovie(Long movieId,MovieRequest movieRequest) throws  ResourceNotFoundException{

        try{
            Movie movie = getById(movieId);

            movie.setGenre(movieRequest.getGenre());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setActors(actorRepository.findActorsByIdIn(movieRequest.getActors()));

            movieRepository.save(movie);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        } catch (ActorMoviePortalException e) {
            throw e;
        }
        catch(Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_UPDATE_MOVIE_BY_ID, e.getMessage());
            throw new ActorMoviePortalException("Unable to update Movie. Try again later.");
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> deleteMovieById(Long movieId) throws ResourceNotFoundException {
        try
        {
            getById(movieId);
            movieRepository.deleteById(movieId);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }
    }

    private Movie getById(Long id) throws ActorMoviePortalException {
        return movieRepository.findById(id).orElseThrow(() -> new ActorMoviePortalException("movie not found by given id :: " + id));
    }
    private ResultStatusResponse generateSuccessMessage() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("SUCCESS");
        return new ResultStatusResponse(resultStatus);
    }
}
