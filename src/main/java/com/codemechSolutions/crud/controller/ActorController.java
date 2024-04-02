package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.entity.Actor;
import com.codemechSolutions.crud.constant.APIConstant;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.request.ActorRequest;
import com.codemechSolutions.crud.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(APIConstant.ACTOR_URL) //api/v1/actors
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ResultStatusResponse> saveActor(@Valid @ModelAttribute ActorRequest actorRequest) throws IOException {
        return actorService.saveActor(actorRequest);
    }

    @GetMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<Actor> getActor(@PathVariable Long id) throws ActorMoviePortalException{
        return actorService.getActorById(id);
    }

    @PutMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<ResultStatusResponse> updateActor(@PathVariable Long id, @Valid @ModelAttribute ActorRequest actorRequest) throws ActorMoviePortalException,IOException {
        System.out.println(actorRequest.getImage().getOriginalFilename());
        return actorService.updateActor(id, actorRequest);
    }

    @DeleteMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<ResultStatusResponse> deleteActor(@PathVariable Long id) throws ActorMoviePortalException {
        return actorService.deleteActorById(id);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() throws ActorMoviePortalException {
        return actorService.getAllActors();
    }
}