package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Session {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SessionName",unique = true,nullable = false)
    private String  nom ;

    @Column(name = "Description")
    private String description;
    @Column(name = "Startdatedate",nullable = false)
    private Date Startdate;
    @Column(name = "EndDate",nullable = false)
    private Date EndDate;

    @OneToMany(mappedBy = "session")
    private Set<Formation>formations ;


    @OneToMany(mappedBy = "session")
    private Set<Enrollement> enrollements=new HashSet<>();

    @Transient
    private Double sessionDuration;

    public Double getSessionDuration() {

            calculateSessionDuration();

        return sessionDuration;
    }

    private void calculateSessionDuration() {
        // Handle missing start or end dates gracefully
        if (getStartdate() == null || getEndDate() == null) {
            sessionDuration = 1.0; // Set a default value (e.g., 1 month) for missing dates
            System.out.println("Warning: Start or end date is missing. Setting session duration to default value.");
            return;  // Exit the method if dates are missing
        }

        DateTimeFormatter formatter;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDateStr = getStartdate().toString().substring(0, 19);
        String endDateStr = getEndDate().toString().substring(0, 19);

        // Use a dedicated date/time library for robustness (e.g., java.time)
        LocalDate startDate = LocalDate.parse( startDateStr,formatter ); // Replace `formatter` with appropriate format
        LocalDate endDate = LocalDate.parse( endDateStr,formatter );

        // Calculate difference in months, handling potential negative values
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        if (months < 0) {
            sessionDuration = 0.0; // Set session duration to 0 if end date is before start date
            System.out.println("Error: End date is before start date. Setting session duration to 0.");
        } else {
            // Round up to nearest whole month if necessary
            sessionDuration = Math.ceil(months);
        }

        System.out.println("Session duration: " + sessionDuration + " months");
    }




}
