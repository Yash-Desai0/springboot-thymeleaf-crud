package com.codemechSolutions.crud.request;

import java.time.LocalDate;
import java.util.List;

public class MovieRequest {

    private String title;

    private LocalDate releaseDate;

    private String genre;

    private List<Long> actors;

    public MovieRequest(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Long> getActors() {
        return actors;
    }

    public void setActors(List<Long> actors) {
        this.actors = actors;
    }
}
