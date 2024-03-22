package com.codemechsolutions.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private LocalDate releaseDate;

    private String genre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actor_movie",
               joinColumns = @JoinColumn(name = "movie_id",referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "actor_id",referencedColumnName = "id"))
    private List<Actor> actors = new ArrayList<>();
}
