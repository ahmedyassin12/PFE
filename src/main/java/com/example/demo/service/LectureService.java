package com.example.demo.service;

import com.example.demo.dao.FormationDAO;
import com.example.demo.dao.LectureDAO;
import com.example.demo.entity.Formation;
import com.example.demo.entity.Lecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LectureService {
    @Autowired
    private LectureDAO lectureDAO;

    public Iterable<Lecture> getAllLecture() {


        return this.lectureDAO.findAll();


    }

    public Lecture getLectureById(Long id) {
        Optional<Lecture> optional = lectureDAO.findById(id);

        Lecture lecture;
        if (optional.isPresent()) lecture = optional.get();
        else {

            throw new RuntimeException("lecture not found for id  ::  " + id);


        }

        return lecture;


    }

    public Lecture getLectureByNom(String nom) {

        Optional<Lecture> optional = lectureDAO.findLectureByNom(nom);

        Lecture lecture;

        if (optional.isPresent()) {

            lecture = optional.get();


        } else {

            throw new RuntimeException("lecture not found for name  ::  " + nom);


        }

        return lecture;


    }

    public Lecture createNewLecture(Lecture lecture) {


        lectureDAO.save(lecture);
        log.info("lecture {} is saved", lecture.getId());

        return lecture;


    }

    public void rem_Lecture(Long id) {

        lectureDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("lecture not found with id: " + id));

        lectureDAO.deleteById(id);


    }

    public Lecture update_Lecture(Lecture lecture) {

        if (lecture == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid formation ID for update");
        }
        return lectureDAO.save(lecture);


    }
}
