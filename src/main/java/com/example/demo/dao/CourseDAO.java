package com.example.demo.dao;

import com.example.demo.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CourseDAO extends CrudRepository<Course, Long> {



    Optional<Course>findByCourseName(String CourseName);



}
