package com.codemechSolutions.crud.service;

import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.request.AuthRequest;
import com.codemechSolutions.crud.request.UserRequest;
import com.codemechSolutions.crud.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthenticationResponse register(UserRequest userRequest) throws ActorMoviePortalException;
    AuthenticationResponse login(AuthRequest authRequest, HttpServletResponse response);
}
