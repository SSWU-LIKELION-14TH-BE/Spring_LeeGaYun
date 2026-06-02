package org.example.session222.w9.config;

import lombok.RequiredArgsConstructor;
import org.example.session222.w9.security.W9JwtTokenFilter;
import org.example.session222.w9.security.W9JwtTokenProvider;
import org.example.session222.w9.service.W9UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class W9SecurityConfig {

    private final W9JwtTokenProvider w9JwtTokenProvider;
    private final W9UserService w9UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/signup", "/api/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new W9JwtTokenFilter(w9JwtTokenProvider, w9UserService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
