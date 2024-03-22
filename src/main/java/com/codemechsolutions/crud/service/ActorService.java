package com.codemechsolutions.crud.service;


import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;

import java.util.List;

public interface ActorService {

    List<Actor> getAllActors();
    Actor saveActor(Actor actor);
    Actor getActorById(long id) throws ResourceNotFoundException;
    void deleteActorById(long id) throws ResourceNotFoundException;
    boolean getActorByUserName(String userName);
    List<Actor> getAllActorsByMovieId(Long movieId) throws ResourceNotFoundException;


}
