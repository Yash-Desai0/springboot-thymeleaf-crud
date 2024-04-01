package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.entity.Actor;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.domain.ResultStatus;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.repository.ActorRepository;
import com.codemechSolutions.crud.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<ResultStatusResponse> saveActor(Actor actor) throws IOException {
        try {
            byte[] decodedData = Base64.getDecoder().decode(actor.getImage());
            actor.setImage(decodedData);
//            if(file != null)
//            {
//                /*byte[] decodedBytes = Base64.getDecoder().decode(encodedString);*/
//                System.out.println(file);
//                actor.setImageName(file.getOriginalFilename());
//                actor.setImage(file.getBytes());
//            }
            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), MET_SAVE_ACTOR, e.getMessage());
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

    public ResponseEntity<ResultStatusResponse> updateActor(Long actorId, Actor actorDetails,MultipartFile file) throws ActorMoviePortalException,IOException {
        try {
            Actor actor = getById(actorId);
            actor.setDateOfBirth(actorDetails.getDateOfBirth());
            actor.setGender(actorDetails.getGender());
            actor.setPhoneNumber(actorDetails.getPhoneNumber());
            actor.setBiography(actorDetails.getBiography());
            try {
                if (file != null)
                {
                    actor.setImageName(file.getOriginalFilename());
                    actor.setImage(file.getBytes());
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
                e.getMessage();
            }
            actorRepository.save(actor);
            return new ResponseEntity<>(generateSuccessMessage(), HttpStatus.OK);
        } catch (ActorMoviePortalException e) {
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
}
