package com.example.demo.dao;

import com.example.demo.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ManagerDAO extends CrudRepository<Manager,Long> {

    Optional<Manager> findByEmail(String Email);

    Optional<Manager> findBynom(String nom);



}
