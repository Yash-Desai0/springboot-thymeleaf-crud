package com.codemechSolutions.crud.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePictureService {

    void saveProfilePicture(Long actorId, MultipartFile file) throws IOException;

    byte[] getProfilePicture(Long actorId);
}
