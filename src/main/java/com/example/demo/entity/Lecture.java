package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private long lecture_id ;
    @Column(name = "lecture_name")
    private String lecture_name;

    @Column(name = "lecture_description")
    private String lecture_description;
    @Column(name = "VideoUrl")
    private String VideoUrl;

    @Column(name = "pdf_url")
    private String pdf_url;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section ;

    @OneToOne(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ressources ressources ;




}
