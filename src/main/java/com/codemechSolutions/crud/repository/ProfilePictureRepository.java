package com.codemechSolutions.crud.repository;

import com.codemechSolutions.crud.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<Image,Long> {
}
