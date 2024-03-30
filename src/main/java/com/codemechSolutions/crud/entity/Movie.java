package com.codemechSolutions.crud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Movie(String title, LocalDate releaseDate, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", actors=" + actors +
                '}';
    }
}
