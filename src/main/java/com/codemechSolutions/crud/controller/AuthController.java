package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.repository.UserRepository;
import com.codemechSolutions.crud.request.AuthRequest;
import com.codemechSolutions.crud.request.UserRequest;
import com.codemechSolutions.crud.response.AuthenticationResponse;
import com.codemechSolutions.crud.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<ResultStatusResponse> createUserHandler(@Valid @RequestBody UserRequest userRequest) throws ActorMoviePortalException {
        return authService.register(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUserHandler(@Valid @RequestBody AuthRequest authRequest, HttpServletResponse response) throws ActorMoviePortalException {

        return authService.login(authRequest, response);
    }
}
