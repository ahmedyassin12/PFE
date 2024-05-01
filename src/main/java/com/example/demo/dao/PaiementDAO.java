package com.example.demo.dao;

import com.example.demo.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository

public interface PaiementDAO extends JpaRepository<Paiement, Long> {

/*
    List<Paiement> findByEnrollement_EnrollementId(Long enrollementId);*/

    List<Paiement> findByStudent_id(long studentId);

}
