package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StudentDAO extends CrudRepository<Student,Long>   {


Optional<Student> findStudentByEmail(String Email);


Optional<Student> findStudentByNom(String nom);


    Optional<Student> findStudentByUsername(String username);

}
