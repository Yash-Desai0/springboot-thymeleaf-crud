package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.constant.JwtConstant;
import com.codemechSolutions.crud.domain.ResultStatus;
import com.codemechSolutions.crud.domain.ResultStatusResponse;
import com.codemechSolutions.crud.entity.Role;
import com.codemechSolutions.crud.entity.User;
import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.mapper.UserMapper;
import com.codemechSolutions.crud.repository.RoleRepository;
import com.codemechSolutions.crud.repository.UserRepository;
import com.codemechSolutions.crud.request.AuthRequest;
import com.codemechSolutions.crud.request.UserRequest;
import com.codemechSolutions.crud.response.AuthenticationResponse;
import com.codemechSolutions.crud.service.AuthService;
import com.codemechSolutions.crud.service.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static com.codemechSolutions.crud.constant.APIConstant.CLS_MET_ERROR;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public ResponseEntity<ResultStatusResponse> register(UserRequest userRequest) throws ActorMoviePortalException {

        if(userRepository.existsByEmail(userRequest.getEmail()))
            return new ResponseEntity<>(generateSuccessMessage("User already registered !!"), HttpStatus.BAD_REQUEST);

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));


        try {
            User user = userMapper.userRequestToUser(userRequest);
            Optional<Role> role = roleRepository.findByName("ROLE_USER");           // set the default ROLE_USER when any user register.
            user.setRoles(Collections.singleton(role.orElseThrow(() -> new ActorMoviePortalException("Role is not found"))));

            userRepository.save(user);
            return new ResponseEntity<>(generateSuccessMessage("SUCCESS"),HttpStatus.CREATED);
        }
        catch (ActorMoviePortalException e) {
            throw e;
        }
        catch (Exception e)
        {
            LOGGER.error(CLS_MET_ERROR, this.getClass(), "register", e.getMessage());
            throw e;
        }

    }

    public ResponseEntity<AuthenticationResponse> login(AuthRequest authRequest, HttpServletResponse response) throws ActorMoviePortalException {

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(()->new ActorMoviePortalException("User not registered yet!!",HttpStatus.BAD_REQUEST));

         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         authRequest.getEmail(),
                         authRequest.getPassword()
                 )
         );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(user);

        // Set JWT token in a cookie
        Cookie cookie = new Cookie(JwtConstant.JWT_HEADER, token);
        cookie.setPath("/");
        cookie.setMaxAge(86400);        // Set expiration time in seconds for 1day
        response.addCookie(cookie);

        return new ResponseEntity<>(AuthenticationResponse.builder()
                .token(token)
                .message("SUCCESS")
                .build(),HttpStatus.OK);
    }

    private ResultStatusResponse generateSuccessMessage(String response) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus(response);
        return new ResultStatusResponse(resultStatus);
    }
}
