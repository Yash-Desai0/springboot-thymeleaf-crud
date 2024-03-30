package com.codemechSolutions.crud.repository;

import com.codemechSolutions.crud.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    /*boolean findByTitle(String title);*/
}
