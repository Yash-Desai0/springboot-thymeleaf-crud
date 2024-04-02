package com.codemechSolutions.crud.request;

import com.codemechSolutions.crud.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ActorRequest {
    private String userName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String biography;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private MultipartFile image;
    private List<Movie> movies;
}
