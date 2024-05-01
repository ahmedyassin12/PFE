package com.example.demo.service;

import com.example.demo.dao.EnrollementDAO;
import com.example.demo.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EnrollementService {

    @Autowired
    private EnrollementDAO enrollementDAO;


    public Iterable<Enrollement> getAllEnrollements() {
        return enrollementDAO.findAll();
    }

    public Enrollement getEnrollementById(Long id) {
        Optional<Enrollement> optional = enrollementDAO.findById(id);
        Enrollement enrollement;
        if (optional.isPresent()) {
            enrollement = optional.get();
        } else {
            throw new RuntimeException("Enrollement not found for id :: " + id);
        }
        return enrollement;
    }

    public Enrollement createNewEnrollement(Enrollement enrollement) {



        enrollementDAO.save(enrollement);
        log.info("Enrollement {} is saved", enrollement.getEnrollement_id());
        return enrollement;


    }

    public void removeEnrollement(Long id) {
        enrollementDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollement not found with id: " + id));
        enrollementDAO.deleteById(id);
    }

    public Enrollement updateEnrollement(Enrollement enrollement) {
        if (enrollement == null || enrollement.getEnrollement_id() == null) {
            throw new IllegalArgumentException("Invalid enrollement ID for update");
        }
        return enrollementDAO.save(enrollement);
    }


}