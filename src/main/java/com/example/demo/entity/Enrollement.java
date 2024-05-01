package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "formation_id"}),
        @UniqueConstraint(columnNames = {"student_id", "session_id"})
})
public class Enrollement {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollement_id")
    private Long enrollement_id ;

    @Column(name="paiement_Status")
    private String paiement_Status ;




    @Column(name = "Enrollement_date")
    private Date enrollement_date;





    @OneToMany(mappedBy = "enrollement",fetch = FetchType.EAGER)
    private List <Paiement> paiement ;

    @ManyToOne
    @JoinColumn(name="Student_id")
    private Student student ;


    @ManyToOne
    @JoinColumn(name = "Formation_id")
    private Formation formation;


    @ManyToOne
    @JoinColumn(name = "Session_id")
    private Session session;





}
