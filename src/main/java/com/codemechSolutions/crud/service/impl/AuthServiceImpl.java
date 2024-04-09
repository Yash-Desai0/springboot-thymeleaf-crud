package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.constant.JwtConstant;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(UserRequest userRequest) throws ActorMoviePortalException {

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User user = userMapper.userRequestToUser(userRequest);
        Optional<Role> role = roleRepository.findByName("ROLE_USER");           // set the default ROLE_USER when any user register.
        user.setRoles(Collections.singleton(role.orElseThrow(() -> new ActorMoviePortalException("Role is not found"))));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(userRepository.save(user)))
                .message("SUCCESS")
                .build();
    }

    public AuthenticationResponse login(AuthRequest authRequest, HttpServletResponse response){
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         authRequest.getEmail(),
                         authRequest.getPassword()
                 )
         );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user =userRepository.findByEmail(authRequest.getEmail())
                 .orElseThrow(()-> new UsernameNotFoundException("Invalid user request !"));

        String token = jwtService.generateToken(user);

        // Set JWT token in a cookie
        Cookie cookie = new Cookie(JwtConstant.JWT_HEADER, token);
        cookie.setPath("/");
        cookie.setMaxAge(86400); // Set expiration time in seconds
        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .token(token)
                .message("SUCCESS")
                .build();
    }
}
