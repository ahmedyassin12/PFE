package com.example.demo.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Section {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private long section_id ;
    @Column(name = "section_name")
    private String section_name;

    @Column(name = "section_startDate")
    private String section_startDate;
    @Column(name = "section_EndDate")
    private String section_EndDate;

@ManyToOne
    @JoinColumn(name="course_id")
    private Course course ;


@OneToMany (mappedBy = "section")
    private Set<Lecture> Lectures =new HashSet<>() ;




}
