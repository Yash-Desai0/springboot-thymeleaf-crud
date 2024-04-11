package com.codemechSolutions.crud.request;

import com.codemechSolutions.crud.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ActorRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 12, message = "Username must be between 3 and 12 characters long")
    private String userName;
    @NotBlank(message = "Gender is required")
    private String gender;

    private LocalDate dateOfBirth;
    @Size(min = 10,max = 10,message = "phone number should be 10 digits")
    private String phoneNumber;
    @Size(max = 50, message = "biography should be up to 50 characters")
    private String biography;

    private MultipartFile image;
    private List<Movie> movies;
}
