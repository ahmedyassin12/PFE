package com.example.demo.service;

import com.example.demo.dao.PaiementDAO;
import com.example.demo.entity.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {


    @Autowired
    private PaiementDAO paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> getPaiementById(long paiementId) {

        return paiementRepository.findById(paiementId);

    }

    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement updatePaiement(long paiementId, Paiement updatedPaiement) {
        if (paiementRepository.existsById(paiementId)) {
            updatedPaiement.setPaiement_id(paiementId);
            return paiementRepository.save(updatedPaiement);
        }
        return null; // Handle non-existing paiement
    }

    public void deletePaiement(long paiementId) {
        paiementRepository.deleteById(paiementId);
    }






}
