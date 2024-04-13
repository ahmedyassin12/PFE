package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Person;
import com.example.demo.dao.PersonDAO;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {

    @Autowired
    private PersonDAO userRepository;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Person>> getAllUsers() {
        List<Person> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable Long id) {
        Person user = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Person> findByEmail(@PathVariable String email) {
        Person user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PostMapping ("/createUser")
    public ResponseEntity<Person> createUser(@RequestBody Person user) {
        Person savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Person> updateUser(@PathVariable Long id, @RequestBody Person userDetails) {
        Person user = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("User not found"));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setNom(userDetails.getNom());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setDateNaissance(userDetails.getDateNaissance());
        Person updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Person user = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("User not found"));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}