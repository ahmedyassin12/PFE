package com.example.demo.service;

import com.example.demo.dao.PaiementDAO;
import com.example.demo.entity.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {


    @Autowired
    private PaiementDAO paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
    public Paiement getpaiementById(long id ){
        Optional<Paiement> optional=paiementRepository.findById(id) ;

        Paiement paiement;
        if(optional.isPresent()) paiement=optional.get();
        else {

            throw new RuntimeException("paiement not found for id  ::  "+id  )  ;


        }

        return paiement ;



    }
    public List<Paiement> getPaiementByStudent_id(long student_Id) {

        List<Paiement> paiements = paiementRepository.findByStudent_id(student_Id);


        if (paiements.isEmpty()) {
            throw new RuntimeException("Paiments not found for student_id :: " + student_Id);
        }
        return paiements;


    }

   /* public List<Paiement>  getPaimentbyEnrollement_id(long id  ){



        List<Paiement> paiements = paiementRepository.findByEnrollement_EnrollementId(id);



        if (paiements.isEmpty()) {
            throw new RuntimeException("Paiments not found for enrollement_id :: " + id);
        }





        return paiements ;


    }*/
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement updatePaiement( Paiement paiment) {
        if (paiment == null ) {
            throw new IllegalArgumentException("Invalid enrollement ID for update");
        }
        paiementRepository.save(paiment) ;
        return paiment;

    }

    public void deletePaiement(long paiementId) {
        paiementRepository.deleteById(paiementId);
    }







}
