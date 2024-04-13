package com.example.demo.service;

import com.example.demo.dao.CourseDAO;
import com.example.demo.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    public Iterable<Course> getAllCourses(){

        return  this.courseDAO.findAll();


    }

    public Course getcourseByNom(String nom  ){

        Optional<Course> optional=courseDAO.findByCourseName(nom);

        Course course ;
        if(optional.isPresent()){

            course = optional.get();

        }

        else {

            throw new RuntimeException("Course not found for nom  ::  "+nom )  ;

        }

        return course ;


    }

    public Course getCourseById(Long id ){
        Optional<Course> optional=courseDAO.findById(id) ;

        Course course;
        if(optional.isPresent()) course=optional.get();
        else {

            throw new RuntimeException("Course not found for id  ::  "+id  )  ;


        }

        return course ;



    }

    public Course createNewCourse(Course course ){


        courseDAO.save(course);
        log.info("Course {} is saved", course.getId());

        return course;


    }

    public void rem_course(Long id ){

        courseDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("course not found with id: " + id));

        courseDAO.deleteById(id);


    }



    public Course update_course(Course course){

        if (course==null||course.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid course ID for update");
        }
        return courseDAO.save(course) ;



    }

}