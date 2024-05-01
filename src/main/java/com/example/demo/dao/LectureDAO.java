package com.example.demo.dao;

import com.example.demo.entity.Lecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureDAO extends CrudRepository<Lecture,Long> {


    Optional<Lecture> findLectureByNom(String nom);
}
