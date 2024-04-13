package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "CourseName",unique = true,nullable = false)
    private String  courseName ;

    @Column(name = "Description")
    private String description;


    @Column(name = "date")
    private Date date;




    @OneToMany(mappedBy = "course")
    private Set<Enrollement>enrollements=new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur ;



    @OneToMany(mappedBy = "course")
    private Set<Section> sections=new HashSet<>() ;



}
