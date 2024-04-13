package com.example.demo.dao;

import com.example.demo.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaiementDAO extends JpaRepository<Paiement, Long> {




}
