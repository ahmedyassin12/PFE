package com.example.demo.dao;

import com.example.demo.entity.Enrollement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollementDAO extends CrudRepository<Enrollement,Long> {



}
