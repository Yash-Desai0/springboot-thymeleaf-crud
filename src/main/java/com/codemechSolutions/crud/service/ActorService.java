package com.codemechSolutions.crud.service;


import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.entity.Actor;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.request.ActorRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ActorService {


    ResponseEntity<Actor> getActorById(Long id) throws ActorMoviePortalException;

    ResponseEntity<List<Actor>> getAllActors() throws  ActorMoviePortalException;

    ResponseEntity<ResultStatusResponse> saveActor(ActorRequest actorRequest) throws ActorMoviePortalException;

    ResponseEntity<ResultStatusResponse> updateActorImage(Long actorId, MultipartFile profilePicture) throws ActorMoviePortalException;

    ResponseEntity<ResultStatusResponse> updateActor(Long id, ActorRequest actorRequest) throws ActorMoviePortalException;

    ResponseEntity<ResultStatusResponse> deleteActorById(Long id) throws ActorMoviePortalException;

   /* void getActorImageById(Long actorId,HttpServletResponse response) throws ActorMoviePortalException;*/

    Actor getById(Long id) throws ActorMoviePortalException;
}
