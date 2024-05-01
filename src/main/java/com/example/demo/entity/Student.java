package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@DiscriminatorValue("STUDENT")
public class Student extends Person {



    @OneToMany(mappedBy = "student")
    private Set<Enrollement> enrollements= new HashSet<>() ;


    @OneToMany(mappedBy="student")
    private Set<Paiement>paiements=new HashSet<>() ;


    @ManyToMany()
    @JoinTable(

            name="student_Events",
            joinColumns =@JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")

    )
    private Set<Event>events=new HashSet<>() ;



//additional attributes :



}
