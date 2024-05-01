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
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "FormationName",unique = true,nullable = false)
    private String  nom ;

    @Column(name = "Description")
    private String description;
    @Column(name = "date",nullable = false)
    private Date date;

    @OneToMany(mappedBy = "formation")
    private Set<Enrollement> enrollements=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "formateur_formation",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "formateur_id"))
    private Set <Formateur> formateurs ;


    @OneToMany(mappedBy = "formation")
    private Set<Course> courses=new HashSet<>() ;


    @ManyToOne
    @JoinColumn(name="Session_id")
    private Session session ;



}
