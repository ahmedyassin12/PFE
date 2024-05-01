package com.example.demo.auth;

import com.example.demo.configuration.JwtService;
import com.example.demo.dao.PersonDAO;
import com.example.demo.entity.Formateur;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Student;
import com.example.demo.entity.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonDAO repository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        Person user;
        if (request.getRole().name().equals("STUDENT")) {
            user = Student.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())
                    .role(request.getRole())
                    .build();
            } else if (request.getRole().name().equals("Formateur")) {
            user = Formateur.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dateNaissance(request.getDateNaissance())
                    .availability(request.getAvailability())
                    .Skills(request.getSkills())
                    .role(request.getRole())
                    .build();
        } else if (request.getRole().name().equals("MANAGER")) {

            user = Manager.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())
                    .role(request.getRole())
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
repository.save(user) ;

var jwtToken=jwtService.generateToken(user) ;

return AuthenticationResponse.builder()

        .token(jwtToken)
        .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws BadRequestException {

        System.out.println("username = "+request.getUsername() +" pass = "+request.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )


            );


        } catch (BadCredentialsException e) {
            System.out.println("username = "+request.getUsername() +" pass = "+request.getPassword());

            throw new BadRequestException("Invalid email or password");
        }


        var user = repository.findByUsername(request.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email address: " + request.getUsername())
        );

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}