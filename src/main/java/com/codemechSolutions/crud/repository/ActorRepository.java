package com.codemechSolutions.crud.repository;

import com.codemechSolutions.crud.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
public interface ActorRepository extends JpaRepository<Actor,Long> {

    /*List<Actor> findByMoviesId(Long movieId);
    boolean existsActorByUserName(String userName);*/

    List<Actor> findActorsByIdIn(Collection<Long> ids);
}
