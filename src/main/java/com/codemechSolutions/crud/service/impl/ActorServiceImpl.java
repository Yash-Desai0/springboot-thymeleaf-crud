package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.entity.Actor;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.domain.ResultStatus;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.repository.ActorRepository;
import com.codemechSolutions.crud.request.ActorRequest;
import com.codemechSolutions.crud.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import static com.codemechSolutions.crud.constant.APIConstant.CLS_MET_ERROR;
import static com.codemechSolutions.crud.constant.APIConstant.MET_GET_ACTOR_BY_ID;
import static com.codemechSolutions.crud.constant.APIConstant.MET_GET_ALL_ACTORS;
import static com.codemechSolutions.crud.constant.APIConstant.MET_SAVE_ACTOR;
import static com.codemechSolutions.crud.constant.APIConstant.MET_UPDATE_ACTOR_BY_ID;
import static com.codemechSolutions.crud.constant.APIConstant.MET_DELETE_ACTOR_BY_ID;

@Service
public class ActorServiceImpl implements ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    @Autowired
    private ActorRepository actorRepository;
    @Override
    public ResponseEntity<Actor> getActorById(Long id) throws ActorMoviePortalException {
        try {
            return new ResponseEntity<>(getById(id), HttpStatus.OK);
        } catch (ActorMoviePortalException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_GET_ACTOR_BY_ID, e.getMessage());
            throw new ActorMoviePortalException("Unable to get Actor. Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<Actor>> getAllActors() throws ActorMoviePortalException {
        try {
            return new ResponseEntity<>(actorRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_GET_ALL_ACTORS, e.getMessage());
            throw new ActorMoviePortalException("Unable to get Actors. Try again later.");
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> saveActor(ActorRequest actorRequest) throws IOException {
        try {
            Actor actor = new Actor();
            actor.setUserName(actorRequest.getUserName());
            actor.setGender(actorRequest.getGender());
            actor.setDateOfBirth(actorRequest.getDateOfBirth());
            actor.setPhoneNumber(actorRequest.getPhoneNumber());
            actor.setBiography(actorRequest.getBiography());
            actor.setImage(actorRequest.getImage().getBytes());
            actor.setImageName(actorRequest.getImage().getOriginalFilename());

            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_SAVE_ACTOR, e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<ResultStatusResponse> updateActor(Long actorId, ActorRequest actorRequest) throws ActorMoviePortalException, IOException {
        try {

            Actor actor = getById(actorId);
            actor.setUserName(actorRequest.getUserName());
            actor.setGender(actorRequest.getGender());
            actor.setDateOfBirth(actorRequest.getDateOfBirth());
            actor.setPhoneNumber(actorRequest.getPhoneNumber());
            actor.setBiography(actorRequest.getBiography());
            actor.setImage(actorRequest.getImage().getBytes());
            actor.setImageName(actorRequest.getImage().getOriginalFilename());

            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(), HttpStatus.OK);
        }
        catch(IOException e)
        {
            e.getMessage();
            e.printStackTrace();
             throw e;
        }
        catch(MultipartException e)
        {
            e.getMessage();
            e.printStackTrace();
            throw e;
        }
        catch (ActorMoviePortalException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_UPDATE_ACTOR_BY_ID, e.getMessage());
            throw new ActorMoviePortalException("Unable to update Actor. Try again later.");
        }
    }

    @Override
    public ResponseEntity<ResultStatusResponse> deleteActorById(Long actorId) throws ActorMoviePortalException {
        try {
            actorRepository.delete(getById(actorId));
            return new ResponseEntity<>(generateSuccessMessage(), HttpStatus.OK);
        } catch (ActorMoviePortalException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_DELETE_ACTOR_BY_ID, e.getMessage());
            throw new ActorMoviePortalException("Unable to delete Actor. Try again later.");
        }
    }

    private Actor getById(Long id) throws ActorMoviePortalException {
        return actorRepository.findById(id) .orElseThrow(() -> new ActorMoviePortalException("Actor not found by given id :: " + id, HttpStatus.BAD_REQUEST));
    }

    private ResultStatusResponse generateSuccessMessage() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("SUCCESS");
        return new ResultStatusResponse(resultStatus);
    }

    /*@Override
    public boolean getActorByUserName(String userName) {
        return actorRepository.findByUserName(userName);
    }*/

    /*@Override
    public List<Actor> getAllActorsByMovieId(Long movieId){
        return actorRepository.findByMoviesId(movieId);
    }*/
}
