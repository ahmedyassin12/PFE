package com.example.demo.dao;

import com.example.demo.entity.Formation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormationDAO extends CrudRepository<Formation,Long> {


    Optional<Formation> findFormationByNom(String nom);


}
