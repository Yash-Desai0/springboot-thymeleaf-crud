package com.codemechSolutions.crud.repository;

import com.codemechSolutions.crud.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor,Long> {

    List<Actor> findActorsByIdIn(Collection<Long> ids);

    boolean existsByUserName(String userName);

}
