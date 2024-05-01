package com.example.demo.dao;

import com.example.demo.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDAO extends CrudRepository<Session,Long> {

    Optional<Session> findSessionByNom(String nom);

}
