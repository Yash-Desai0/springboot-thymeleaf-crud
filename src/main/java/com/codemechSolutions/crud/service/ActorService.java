package com.codemechSolutions.crud.service;


import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.entity.Actor;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ActorService {

    ResponseEntity<Actor> getActorById(Long id) throws ActorMoviePortalException;

    ResponseEntity<List<Actor>> getAllActors() throws  ActorMoviePortalException;

    ResponseEntity<ResultStatusResponse> saveActor(Actor actor) throws IOException;

    ResponseEntity<ResultStatusResponse> updateActor(Long id, Actor actor,MultipartFile file) throws ActorMoviePortalException,IOException;

    ResponseEntity<ResultStatusResponse> deleteActorById(Long id) throws ActorMoviePortalException;

}
