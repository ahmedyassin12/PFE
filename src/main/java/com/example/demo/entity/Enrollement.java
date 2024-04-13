package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Enrollement {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Enrollement_id")
    private Long Enrollement_id ;

    @Column(name = "Enrollement_date")
    private Date Enrollement_date;

    @Column(name = "grade")
    private String grade ;

    @ManyToOne
    @JoinColumn(name="Student_id")
    private Student student ;



    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course ;








}
