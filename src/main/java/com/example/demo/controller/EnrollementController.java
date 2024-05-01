package com.example.demo.controller;

import com.example.demo.entity.Enrollement;
import com.example.demo.service.EnrollementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class EnrollementController {

    @Autowired
    private EnrollementService enrollementService;

    @GetMapping("/getAllEnrollements")
    public Iterable<Enrollement> getAllEnrollements() {
        return enrollementService.getAllEnrollements();

    }

    @GetMapping("/getEnrollementById/{id}")
    public Enrollement getEnrollementById(@PathVariable Long id) {
        return enrollementService.getEnrollementById(id);
    }

    @PostMapping("/createNewEnrollement")
    public Enrollement createNewEnrollement(@RequestBody Enrollement enrollement) {
        return enrollementService.createNewEnrollement(enrollement);

    }

    @DeleteMapping("/removeEnrollement/{id}")
    public void removeEnrollement(@PathVariable Long id) {
        enrollementService.removeEnrollement(id);
    }


    @PutMapping("/updateEnrollement")
    public Enrollement updateEnrollement(@RequestBody Enrollement enrollement) {

        return enrollementService.updateEnrollement(enrollement);

    }
}