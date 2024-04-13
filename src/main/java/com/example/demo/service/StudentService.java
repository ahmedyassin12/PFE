package com.example.demo.service;


import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    @Autowired
   private StudentDAO studentDAO ;


    public Iterable<Student> getAllStudents(){


        return  this.studentDAO.findAll();


    }


    public Student getStudentByEmail(String Email  ){

        Optional<Student> optional=studentDAO.findByEmail(Email) ;

        Student stdnt ;
        if(optional.isPresent()){

            stdnt = optional.get();

        }

        else {

            throw new RuntimeException("Student not found for email  ::  "+Email )  ;

        }

        return stdnt ;


    }
    public Student getstudentbyId(int id ){
        Optional<Student> optional=studentDAO.findById(id) ;

        Student stdnt;
        if(optional.isPresent()) stdnt=optional.get();
        else {

            throw new RuntimeException("Student not found for id  ::  "+id  )  ;


        }

        return stdnt ;



    }
    public Student getStudentbyNom(String nom  ){

        Optional<Student> optional=studentDAO.findBynom(nom) ;

        Student stdnt ;

        if(optional.isPresent()){

            stdnt = optional.get();


        }

        else {

            throw new RuntimeException("Student not found for name  ::  "+nom  )  ;


        }

        return stdnt ;


    }

    public Student createNewStudent(Student student ){


        studentDAO.save(student);
        log.info("Student {} is saved", student.getId());

        return student;


    }
    public void initStudent() throws ParseException {

        String dateString = "05/12/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse(dateString);

        Student student =new Student() ;

        /* student.builder().nom("ssss")
                .email("smlfjkqsmldjf")
                .password("smdlfjkqs")
                 .phoneNumber("2112058")

                .build();

*/
        student.setNom("ssss");
        student.setEmail("smlfjkqsmldjf");
        student.setPassword("smdlfjkqs");
        student.setPhoneNumber("2112058");
        student.setDateNaissance(date);
        student.setRole(Role.STUDENT);
        studentDAO.save(student);



    }


    public void rem_student(int id ){

        studentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found with id: " + id));

        studentDAO.deleteById(id);


    }



    public Student update_student(Student student){

        if (student==null||student.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid student ID for update");
        }
        return studentDAO.save(student) ;



    }

}
