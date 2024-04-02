package com.codemechSolutions.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String biography;
    private String imageName;
    @Lob
    private byte[] image;

    @JsonIgnore
    @ManyToMany(mappedBy = "actors",cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    public List<Movie> movies = new ArrayList<>();

    public Actor(String userName, String gender, LocalDate dateOfBirth, String phoneNumber, String biography) {
        this.userName = userName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", biography='" + biography +
                '}';
    }
}
