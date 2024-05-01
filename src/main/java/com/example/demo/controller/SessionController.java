package com.example.demo.controller;
import com.example.demo.entity.Session;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SessionController {

    @Autowired
    SessionService sessionService ;








    @GetMapping("/getAllSession")
    public ResponseEntity<Iterable<Session>> getAllSession() {

        Iterable<Session> sessions = sessionService.getAllSession();

        return new ResponseEntity<>(sessions, HttpStatus.OK);



    }

    @GetMapping("/getSessionById/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable("id") Long id) {

        Session session = sessionService.getSessionById(id);
        System.out.println("session  : " + session);
        return new ResponseEntity<>(session, HttpStatus.OK);

    }

    @PostMapping({"/createNewSession"})
    public ResponseEntity<Session> createNewSession(@RequestBody Session session) {


        return new ResponseEntity<>(sessionService.createNewSession(session), HttpStatus.OK);



    }

    @GetMapping("/getSessionByNom/{nom}")
    public ResponseEntity<Session> getSessionByNom(@PathVariable("nom") String nom) {

        Session session = sessionService.getSessionByNom(nom);
        System.out.println("sesison  : " + session);
        return new ResponseEntity<>(session, HttpStatus.OK);

    }


    @DeleteMapping("/rem_session/{id}")

    public ResponseEntity<?> rem_session(@PathVariable("id")  Long id ){

        sessionService.rem_Session(id);
        return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/update_session")
    public  ResponseEntity<Session> update_session(@RequestBody Optional<Session> optionalSession){

        Session session =optionalSession.orElseThrow(() -> new IllegalArgumentException("Invalid session  for update"));

        return new ResponseEntity<>(sessionService.update_Session(session),HttpStatus.OK);

    }




}
