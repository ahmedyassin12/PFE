package com.example.demo.service;

import com.example.demo.dao.FormationDAO;
import com.example.demo.dao.SessionDAO;
import com.example.demo.entity.Formation;
import com.example.demo.entity.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessionService {

    @Autowired
    private SessionDAO sessionDAO ;
    public Iterable<Session> getAllSession(){


        return  this.sessionDAO.findAll();


    }

    public Session getSessionById(Long id ){
        Optional<Session> optional=sessionDAO.findById(id) ;

        Session session;
        if(optional.isPresent()) session=optional.get();
        else {

            throw new RuntimeException("Session not found for id  ::  "+id  )  ;


        }

        return session ;



    }
    public Session getSessionByNom(String nom  ){

        Optional<Session> optional=sessionDAO.findSessionByNom(nom) ;

        Session session ;

        if(optional.isPresent()){

            session = optional.get();


        }

        else {

            throw new RuntimeException("Session not found for name  ::  "+nom  )  ;


        }

        return session ;


    }

    public Session createNewSession(Session session ){


        sessionDAO.save(session);
        log.info("session {} is saved", session.getId());

        return session;


    }

    public void rem_Session(Long id ){

        sessionDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));

        sessionDAO.deleteById(id);


    }
    public Session update_Session(Session session){

        if (session==null||session.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid session ID for update");
        }
        return sessionDAO.save(session) ;



    }
}
