package com.codemechsolutions.crud.service.impl;

import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.domain.ResultStatus;
import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.repository.ActorRepository;
import com.codemechsolutions.crud.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

import static com.codemechsolutions.crud.constant.APIConstant.CLS_MET_ERROR;

@Service
public class ActorServiceImpl implements ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);
    private static final String MET_FETCH_MOVIE_BY_ID = "fetchMovieById";

    @Autowired
    private ActorRepository actorRepository;

    private Actor getById(Long id) throws ResourceNotFoundException {
        return actorRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Actor not found by given id :: "+id));
    }

    @Override
    public ResponseEntity<Actor> getActorById(Long id) throws ResourceNotFoundException {
        try
        {
            return new ResponseEntity<>(getById(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }
    }
    @Override
    public ResponseEntity<List<Actor>> getAllActors(){
        try
        {
            return new ResponseEntity<>(actorRepository.findAll(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> saveActor(Actor actor){
        try{
            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }

    }

    /*@Override
    public boolean getActorByUserName(String userName) {
        return actorRepository.findByUserName(userName);
    }*/

    /*@Override
    public List<Actor> getAllActorsByMovieId(Long movieId){
        return actorRepository.findByMoviesId(movieId);
    }*/

    public ResponseEntity<ResultStatusResponse> updateActor(Long actorId,Actor actorDetails) throws  ResourceNotFoundException{

        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found for this id :: " + actorId));

        actor.setDateOfBirth(actor.getDateOfBirth());
        actor.setGender(actor.getGender());
        actor.setPhoneNumber(actorDetails.getPhoneNumber());
        actor.setBiography(actorDetails.getBiography());
        try{
            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> deleteActorById(Long actorId) {
        try
        {
            actorRepository.deleteById(actorId);
            return new ResponseEntity<>(generateSuccessMessage(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_FETCH_MOVIE_BY_ID, e.getMessage());
            throw e;
        }
    }

    private ResultStatusResponse generateSuccessMessage() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("SUCCESS");
        return new ResultStatusResponse(resultStatus);
    }
}
