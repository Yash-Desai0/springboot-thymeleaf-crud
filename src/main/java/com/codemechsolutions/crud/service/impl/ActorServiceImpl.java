package com.codemechsolutions.crud.service.impl;

import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.repository.ActorRepository;
import com.codemechsolutions.crud.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor getActorById(long id) throws ResourceNotFoundException{
        return actorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Actor not found for this id :: "+id));
    }

    @Override
    public void deleteActorById(long id) throws ResourceNotFoundException{
         actorRepository.delete(actorRepository.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("Actor not found for this id :: "+id)));
    }
    @Override
    public boolean getActorByUserName(String userName) {
        return actorRepository.findByUserName(userName);
    }

    @Override
    public List<Actor> getAllActorsByMovieId(Long movieId){
        return actorRepository.findByMoviesId(movieId);
    }
}
