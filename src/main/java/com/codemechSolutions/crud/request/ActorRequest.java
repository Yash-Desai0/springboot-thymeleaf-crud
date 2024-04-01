package com.codemechSolutions.crud.request;

import com.codemechSolutions.crud.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ActorRequest {
    private String userName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phone;
    private String biography;
    private List<Movie> movies;
}
