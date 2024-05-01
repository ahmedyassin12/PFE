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
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private long paiement_id ;

    @Column(name = "amount")
    private int amount ;

    @Column(name = "paimentDate")
    private Date paimentDate ;



    @ManyToOne
    @JoinColumn(name="enrollement_id")
    private Enrollement enrollement ;


    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student ;




}
