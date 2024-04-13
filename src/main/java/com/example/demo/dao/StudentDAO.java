package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StudentDAO extends CrudRepository<Student,Integer>   {


Optional<Student> findByEmail(String Email);


Optional<Student> findBynom(String nom);



}
