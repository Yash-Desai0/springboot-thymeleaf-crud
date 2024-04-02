package com.codemechSolutions.crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image_table")
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String imageName;

    private String type;

    private byte[] picByte;

    @OneToOne
    @JoinColumn(name = "actor_id",referencedColumnName = "id")
    private Actor actorId;

}
