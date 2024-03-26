package com.codemechsolutions.crud.service;


import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActorService {

    ResponseEntity<Actor> getActorById(Long id) throws ResourceNotFoundException;

    ResponseEntity<List<Actor>> getAllActors() throws  ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> saveActor(Actor actor);

    ResponseEntity<ResultStatusResponse> updateActor(Long id, Actor actor) throws ResourceNotFoundException;

    ResponseEntity<ResultStatusResponse> deleteActorById(Long id) throws ResourceNotFoundException;

}
