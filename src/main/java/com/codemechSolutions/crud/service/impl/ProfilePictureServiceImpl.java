package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.service.ProfilePictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService{
    @Override
    public void saveProfilePicture(Long actorId, MultipartFile file) throws IOException {

    }

    @Override
    public byte[] getProfilePicture(Long actorId) {
        return new byte[0];
    }
}
