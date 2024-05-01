package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor

public class PersonService {

    @Autowired
   private PersonDAO personDAO ;


    public Iterable<Person> getAllUsers(){


        return personDAO.findAll();


    }
    public Person findUserByEmail(String Email  ){

        Optional<Person> optional=personDAO.findUserByEmail(Email) ;

        Person person ;
        if(optional.isPresent()){

            person = optional.get();

        }

        else {

            throw new RuntimeException("User not found for email  ::  "+Email )  ;

        }

        return person ;


    }

    public Person getUserByUsername(String username  ){

        Optional<Person> optional=personDAO.findByUsername(username) ;

        Person person ;
        if(optional.isPresent()){

            person = optional.get();

        }

        else {

            throw new RuntimeException("User not found for username  ::  "+username )  ;

        }

        return person ;


    }
    public Person getUserbyId(Long id ){
        Optional<Person> optional=personDAO.findById(id) ;

        Person user;
        if(optional.isPresent()) user=optional.get();
        else {

            throw new RuntimeException("Student not found for id  ::  "+id  )  ;


        }

        return user ;



    }

    public Person createNewUser(Person user ){


        personDAO.save(user);
        log.info("user {} is saved", user.getId());

        return user;


    }
    public void rem_user(long id ){

        personDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id: " + id));

        personDAO.deleteById(id);


    }
    public Person update_user(Person user){

        if (user==null||user.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid student ID for update");
        }
        return personDAO.save(user) ;



    }


}
