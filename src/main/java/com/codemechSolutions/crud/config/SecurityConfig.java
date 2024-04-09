package com.codemechSolutions.crud.config;

import com.codemechSolutions.crud.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .authorizeHttpRequests(authorize->
                    authorize.requestMatchers("/api/auth/**","/css/**","/login","/js/**","/register","/","/index","/actors","movies").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                            .requestMatchers(HttpMethod.POST,"/api/**").hasAuthority("ROLE_ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/api/**").hasAuthority("ROLE_ADMIN")
                            .requestMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("ROLE_ADMIN") )

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
