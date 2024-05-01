package com.example.demo.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@DiscriminatorValue("Manager")
public class Manager extends Person {






}
