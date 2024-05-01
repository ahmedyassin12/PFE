package com.example.demo.service;
import com.example.demo.dao.FormateurDAO;
import com.example.demo.entity.Formateur;
import com.example.demo.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormateurService {



    @Autowired
    private final PasswordEncoder passwordEncoder ;

        @Autowired
        private FormateurDAO formateurDAO ;


        public Iterable<Formateur> getallFormateur(){


            return  this.formateurDAO.findAll();


        }


        public Formateur getFormateurByEmail(String Email  ){

            Optional<Formateur> optional=formateurDAO.findFormateurByEmail(Email) ;

            Formateur formateur ;
            if(optional.isPresent()){

                formateur = optional.get();

            }

            else {

                throw new RuntimeException("Student not found for email  ::  "+Email )  ;

            }

            return formateur ;

        }
        public Formateur getFormateurByNom(String nom  ){

            Optional<Formateur> optional=formateurDAO.findFormateurBynom(nom) ;

            Formateur formateur ;

            if(optional.isPresent()){

                formateur = optional.get();


            }

            else {

                throw new RuntimeException("formateur not found for name  ::  "+nom  )  ;


            }

            return formateur ;


        }
    public Formateur getFormateurByUsername(String username  ){

        Optional<Formateur> optional=formateurDAO.findFormateurByUsername(username) ;

        Formateur formateur ;

        if(optional.isPresent()){

            formateur = optional.get();


        }

        else {

            throw new RuntimeException("formateur not found for name  ::  "+username  )  ;


        }

        return formateur ;


    }
    public Formateur getFormateurById(long id  ){

        Optional<Formateur> optional=formateurDAO.findById(id) ;

        Formateur formateur ;

        if(optional.isPresent()){

            formateur = optional.get();


        }

        else {

            throw new RuntimeException("formateur not found for id  ::  "+id  )  ;


        }

        return formateur ;


    }

        public Formateur createNewFormateur(Formateur formateur ){


            return formateurDAO.save(formateur);


        }
        public void initFormateur() throws ParseException {

            String dateString = "05/12/2002";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse(dateString);

            Formateur formateur =new Formateur().builder()
                    .dateNaissance(date)
                    .email("qsdmlj@jj.kk")
                    .password(passwordEncoder.encode("hh"))
                    .nom("3abes")
                    .phoneNumber("21123058")
                    .role(Role.Formateur)
                    .username("ff")
            .build() ;


            formateurDAO.save(formateur);



        }


        public void rem_formateur(long id ){

            formateurDAO.findById(id)
                    .orElseThrow(() -> new RuntimeException("formateur not found with id: " + id));

            formateurDAO.deleteById(id);



        }



        public Formateur update_formateur(Formateur formateur){

            if (formateur==null||formateur.getId() == null) {
                // Handle the case where ID is not set or invalid
                // You might want to throw an exception or handle it appropriately
                throw new IllegalArgumentException("Invalid formateur ID for update");
            }
            return formateurDAO.save(formateur) ;



        }

    }



