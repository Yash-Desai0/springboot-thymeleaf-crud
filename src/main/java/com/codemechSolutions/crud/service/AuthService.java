package com.codemechSolutions.crud.service;

import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.request.AuthRequest;
import com.codemechSolutions.crud.request.UserRequest;
import com.codemechSolutions.crud.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResultStatusResponse>  register(UserRequest userRequest) throws ActorMoviePortalException;
    ResponseEntity<AuthenticationResponse> login(AuthRequest authRequest, HttpServletResponse response) throws ActorMoviePortalException;
}
