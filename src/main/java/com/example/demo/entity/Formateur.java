package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@DiscriminatorValue("FORMATEUR")
public class Formateur extends Person {


    @Column
    private String Skills ;

    @Column
    private String availability ;






    @OneToMany(mappedBy = "formateur")
    private Set<Course> courses=new HashSet<>() ;

//additional attributes :

    @ManyToMany()
    @JoinTable(

            name="formateur_Events",
            joinColumns =@JoinColumn(name="formateur_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")


    )
    private Set<Event>events=new HashSet<>() ;




}
