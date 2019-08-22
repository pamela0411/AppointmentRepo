package com.org.nielsen.scheduler;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.org.nielsen.model.CarAppointmentDetails;
import com.org.nielsen.service.CarAppointmentService;

@Configuration
@EnableScheduling
public class ScheduledJob {
	
	
	    @Autowired
	    CarAppointmentService carAppointmentService;
	
	    //Method to run scheduled job of creating new appointments every 6 hours
	    @Scheduled(fixedRate = 21600000)
	    public void createRandomAppointments() throws Exception {
	    	
	    	CarAppointmentDetails newCarAppointmentDetails = new CarAppointmentDetails(); 
    	    newCarAppointmentDetails.setAppointmentDate(java.sql.Date.valueOf(LocalDate.now()));
	    	newCarAppointmentDetails.setAssignedTo("John");
	    	newCarAppointmentDetails.setUserId(0);
	    	newCarAppointmentDetails.setAddress("DEFAULT");
	    	newCarAppointmentDetails.setUserName("DEFAULT");
	    	newCarAppointmentDetails.setCarId(0);
	    	newCarAppointmentDetails.setDeliveryDate(java.sql.Date.valueOf(LocalDate.now()));
	    	newCarAppointmentDetails.setEmailId("DEFAULT");
	    	newCarAppointmentDetails.setModelId("DEFAULT");
	    	newCarAppointmentDetails.setModelName("DEFAULT");
	    	newCarAppointmentDetails.setPrice(0);
	    	newCarAppointmentDetails.setStatus("NEW");
	    	newCarAppointmentDetails.setUserName("DEFAULT");
	    	
	    	carAppointmentService.createNewAppointment(newCarAppointmentDetails);
	    }

}
