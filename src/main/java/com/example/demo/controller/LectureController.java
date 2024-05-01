package com.example.demo.controller;
import com.example.demo.entity.Lecture;
import com.example.demo.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class LectureController {
    @Autowired
    LectureService lectureService ;


    @GetMapping("/getAllLecture")
    public ResponseEntity<Iterable<Lecture>> getAllLecture() {

        Iterable<Lecture> lectures = lectureService.getAllLecture();

        return new ResponseEntity<>(lectures, HttpStatus.OK);

    }

    @GetMapping("/getLectureById/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable("id") Long id) {

        Lecture lecture = lectureService.getLectureById(id);
        System.out.println("lecture  : " + lecture);
        return new ResponseEntity<>(lecture, HttpStatus.OK);

    }

    @PostMapping({"/createNewLecture"})
    public ResponseEntity<Lecture> createNewLecture(@RequestBody Lecture lecture) {


        return new ResponseEntity<>(lectureService.createNewLecture(lecture), HttpStatus.OK);



    }

    @GetMapping("/getLectureByNom/{nom}")
    public ResponseEntity<Lecture> getLectureByNom(@PathVariable("nom") String nom) {

        Lecture lecture = lectureService.getLectureByNom(nom);
        System.out.println("lecture  : " + lecture);
        return new ResponseEntity<>(lecture, HttpStatus.OK);

    }


    @DeleteMapping("/rem_lecture/{id}")

    public ResponseEntity<?> rem_lecture(@PathVariable("id")  Long id ){

        lectureService.rem_Lecture(id);
        return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/update_lecture")
    public  ResponseEntity<Lecture> update_lecture(@RequestBody Optional<Lecture> optionalLecture){

        Lecture lecture =optionalLecture.orElseThrow(() -> new IllegalArgumentException("Invalid lecture  for update"));

        return new ResponseEntity<>(lectureService.update_Lecture(lecture),HttpStatus.OK);

    }


}
