package com.codemechSolutions.crud.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieRequest {

    private String title;
    private LocalDate releaseDate;
    private String genre;
    private List<Long> actors;

    public MovieRequest(){
        super();
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setActors(List<Long> actors) {
        this.actors = actors;
    }
}
