package com.example.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.entity.Permission.* ;

import static com.example.demo.entity.Permission.*;
import static com.example.demo.entity.Role.MANAGER;
import static com.example.demo.entity.Role.STUDENT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception {

    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req->

                      //      req  .requestMatchers("/api/v1/auth/*","/api/v1/Student/*","/api/v3/Paiement/*","/api/v2/Enrollement")
//.permitAll()
                               //     .requestMatchers( "/api/v1/Student/**").hasAnyRole("STUDENT", "MANAGER")

                                   // .requestMatchers(GET, "/api/v1/Student/**").hasAnyAuthority(MANAGER_READ.name(), STUDENT_READ.name())

                     req               .anyRequest()

                    .permitAll()

            )

          .sessionManagement(session -> session.sessionCreationPolicy(STATELESS) )

            .authenticationProvider(authenticationProvider )

            .addFilterBefore(jwtAuthFilter  , UsernamePasswordAuthenticationFilter.class);
/*
      .logout(logout->
            logout.url("api/v1/auth/logout")
                logout.addlogoutHandler(logoutHandler)
        logout.successHandler(request,response,authentication)->SecurityContext...
                )
*/

    return http.build() ;



}


}
