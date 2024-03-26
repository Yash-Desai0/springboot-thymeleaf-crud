package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.constant.APIConstant;
import com.codemechsolutions.crud.domain.ResultStatusResponse;
import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(APIConstant.ACTOR_URL)
public class ActorRestController {

    private final ActorService actorService;

    public ActorRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ResultStatusResponse> saveActor(@Valid @RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @GetMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<Actor> getActor(@PathVariable Long id) throws ResourceNotFoundException {
        return actorService.getActorById(id);
    }

    @PutMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<ResultStatusResponse> updateActor(@PathVariable Long id, @Valid @RequestBody Actor actor) throws ResourceNotFoundException {
        return actorService.updateActor(id, actor);
    }

    @DeleteMapping(APIConstant.ACTOR_ID)
    public ResponseEntity<ResultStatusResponse> deleteActor(@PathVariable Long id) throws ResourceNotFoundException {
        return actorService.deleteActorById(id);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() throws ResourceNotFoundException {
        return actorService.getAllActors();
    }
}