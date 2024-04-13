package com.example.demo;

import com.example.demo.configuration.ApplicationConfig;
import com.example.demo.dao.PersonDAO;
import com.example.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

public class UserDetailsServiceTest {

    @Test
    public void testLoadUserByUsername_WhenUserExists_ExpectUserDetails() {
        // Arrange
        String email = "qsdjj.kk";
        String password = "namnamnam";
        PersonDAO repository = Mockito.mock(PersonDAO.class);
        Mockito.when(repository.findByEmail(email))
                .thenReturn(Optional.of(new Person(email, password)));
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setRepository(repository);
        UserDetailsService userDetailsService =  applicationConfig.UserDetailsService();

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Assert
       if( email.equals(userDetails.getUsername()) ) {
            System.out.println("email working ");
           System.out.println("email = " +userDetails.getUsername());
        }
        if(password.equals(userDetails.getPassword()))             System.out.println("pass working ");

    }

    @Test
    public void testLoadUserByUsername_WhenUserDoesNotExist_ExpectUsernameNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";
        PersonDAO repository = Mockito.mock(PersonDAO.class);
        Mockito.when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setRepository(repository);
        UserDetailsService userDetailsService = applicationConfig.UserDetailsService();

        // Act
        Exception exception = null;
        try {
            userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            exception = e;
        }

        // Assert
        assertNotNull(exception);
        assertEquals("User not found", exception.getMessage());
    }
}
