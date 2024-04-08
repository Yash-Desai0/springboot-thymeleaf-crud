package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.repository.UserRepository;
import com.codemechSolutions.crud.request.AuthRequest;
import com.codemechSolutions.crud.request.UserRequest;
import com.codemechSolutions.crud.response.AuthenticationResponse;
import com.codemechSolutions.crud.service.AuthService;
import com.codemechSolutions.crud.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createUserHandler(@Valid @RequestBody UserRequest userRequest) throws UsernameNotFoundException, ActorMoviePortalException {

        if(userRepository.existsByEmail(userRequest.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AuthenticationResponse>(authService.register(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUserHandler(@Valid @RequestBody AuthRequest authRequest) {
        return new ResponseEntity<AuthenticationResponse>(authService.login(authRequest), HttpStatus.OK);
    }
}
