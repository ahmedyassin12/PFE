package com.example.demo.Schedule;
import com.example.demo.entity.Enrollement;
import com.example.demo.entity.Paiement;
import com.example.demo.service.EnrollementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Component
public class PaimentSchedule {
    @Autowired
    private EnrollementService enrollementService ;
    private Date paiment_date ;

    private Paiement last_paiment ;
    private List<Paiement> payments ;
    private double duration ;
    @Scheduled(cron = "5 * * * * *")
    public void update_Paimenet(){

        for (Enrollement enrollement: enrollementService.getAllEnrollements()
             ) {


            duration = 1 ;
            if(enrollement.getSession()!= null )duration=enrollement.getSession().getSessionDuration() ;


             payments = enrollement.getPaiement();
             if(!payments.isEmpty()) {
                 last_paiment = payments.get(payments.size() - 1);
                 paiment_date = last_paiment.getPaimentDate();

                 System.out.println("duration = "+duration);

                 if ( (duration*30)-calculateDaysSince(paiment_date)   >0 ){

                     enrollement.setPaiement_Status("paid");

                 }
                 else  {

                 enrollement.setPaiement_Status("unpaid");

                 }
                 enrollementService.updateEnrollement(enrollement);
             }

        }



    }


    private double calculateDaysSince(Date lastPaymentDate) {
        Calendar today = Calendar.getInstance();
        Calendar paymentDate = Calendar.getInstance();

        paymentDate.setTime(lastPaymentDate);

        long differenceInTime = today.getTimeInMillis() - paymentDate.getTimeInMillis();

        // Convert milliseconds to days, rounding up if necessary
        return Math.ceil((double) differenceInTime / (1000 * 60 * 60 * 24));

    }




}
