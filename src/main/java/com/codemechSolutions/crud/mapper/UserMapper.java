package com.codemechSolutions.crud.mapper;

import com.codemechSolutions.crud.entity.User;
import com.codemechSolutions.crud.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User userRequestToUser(UserRequest userRequest){
        return User.builder().name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}