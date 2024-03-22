package com.codemechsolutions.crud.repository;

import com.codemechsolutions.crud.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    boolean findByTitle(String title);
}
