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
        if (request.getRole().equals("STUDENT")) {
            user = Student.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())

                    .build();
            } else if (request.getRole().equals("Formateur")) {
            user = Formateur.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dateNaissance(request.getDateNaissance())
                    .availability(request.getAvailability())
                    .Skills(request.getSkills())
                    .build();
        } else if (request.getRole().equals("MANAGER")) {
            user = Manager.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())


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

        try {

            System.out.println("user : "+request.getEmail()+" + "+ request.getPassword()+", is retreated ");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )

            );
            System.out.println("user : "+request.getEmail()+", is retreated ");

        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid email or password");
        }

        System.out.println("user : "+request.getEmail()+", is retreated ");
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email address: " + request.getEmail())
        );

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
