package com.codemechsolutions.crud.repository;

import com.codemechsolutions.crud.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    List<Actor> findByMoviesId(Long movieId);

    boolean findByUserName(String userName);
}
