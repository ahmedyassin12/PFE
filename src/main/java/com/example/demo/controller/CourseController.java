package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<Iterable<Course>> getAllCourses() {

        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);

    }

    @GetMapping("/getcourseByNom/{nom}")
    public ResponseEntity<Course> getcourseByNom(@PathVariable("nom") String nom) {

        Course course = courseService.getcourseByNom(nom);
        System.out.println("course : " + course);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {

        Course course = courseService.getCourseById(id);
        System.out.println("course : " + course);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @PostMapping("/createNewCourse")
    public ResponseEntity<Course> createNewCourse(@RequestBody Course course) {

        courseService.createNewCourse(course);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @DeleteMapping("/rem_course/{id}")
    public ResponseEntity<?> rem_course(@PathVariable("id") Long id) {

        courseService.rem_course(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/update_course")
    public ResponseEntity<Course> update_course(@RequestBody Optional<Course> optionalCourse) {

        Course course = optionalCourse.orElseThrow(() -> new IllegalArgumentException("Invalid course ID for update"));

        return new ResponseEntity<>(courseService.update_course(course), HttpStatus.OK);

    }

}