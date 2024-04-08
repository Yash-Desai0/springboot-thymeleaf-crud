/*
package com.codemechSolutions.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class FormBasedLogin {

    private static final String[] WHITELABEL_URLS = { "/index", "/", "/login","/register","/css/**","/js/**"};

    private static final String[] WEB_AUTH_URLS = { "/actors","/movies"};

    @Bean
    SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(WHITELABEL_URLS).permitAll()
                        .requestMatchers(WEB_AUTH_URLS).authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/actors")
                                .failureUrl("/login")
                                .permitAll())
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
        return http.build();
    }
}
*/
