package com.codemechSolutions.crud.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "role name cannot be empty")
    @Column(unique = true, nullable = false,length = 20)
    private String name;
}