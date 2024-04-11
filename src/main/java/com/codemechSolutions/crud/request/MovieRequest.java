package com.codemechSolutions.crud.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters long")
    private String title;

    private LocalDate releaseDate;

    @Pattern(regexp = "Action|Comedy|Drama|Horror", message = "Genre should be Action,Comedy,Drama or Horror")
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
