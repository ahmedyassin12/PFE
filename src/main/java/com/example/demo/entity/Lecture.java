package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private long id ;
    @Column(name = "lecture_name")
    private String nom;

    @Column(name = "lecture_description")
    private String lecture_description;
    @Column(name = "VideoUrl")
    private String VideoUrl;

    @Column(name = "pdf_url")
    private String pdf_url;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course ;

    @OneToOne(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ressources ressources ;




}
