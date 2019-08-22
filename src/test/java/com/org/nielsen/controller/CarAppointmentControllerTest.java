package com.org.nielsen.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.nielsen.model.CarAppointmentDetails;
import com.org.nielsen.service.CarAppointmentServiceImpl;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(controllers = CarAppointmentController.class)
class CarAppointmentControllerTest {

	
	@MockBean
	CarAppointmentServiceImpl carAppointmentService;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	
    @Autowired
	 MockMvc mvc;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
	
	}

	@Test
	void testDeleteAppointment() throws Exception {	 
		
		 when(carAppointmentService.appointmentExists(1)).thenReturn(true);
		
		  mvc.perform(delete("/car/appointment/delete/1")
		 .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()); 
		
	}

	@Test
	void testCreateNewAppointment() throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String startDateInString = "2019-08-21";
    	String endDateInString = "2019-08-22";
    	String dob = "1990-08-22";
    	
    	//convert String to LocalDate
    	LocalDate startLocalDate = LocalDate.parse(startDateInString, formatter);
    	java.sql.Date startDate = java.sql.Date.valueOf(startLocalDate);


    	LocalDate endLocalDate = LocalDate.parse(endDateInString, formatter);
    	java.sql.Date endDate = java.sql.Date.valueOf(endLocalDate);
    	
    	LocalDate dobLocalDate = LocalDate.parse(dob, formatter);
    	java.sql.Date dobDate = java.sql.Date.valueOf(dobLocalDate);
    	
    	CarAppointmentDetails carAppointmentDetails = new CarAppointmentDetails();
		carAppointmentDetails.setAssignedTo("John");
		carAppointmentDetails.setUserId(0);
		carAppointmentDetails.setAddress("DEFAULT");
		carAppointmentDetails.setUserName("DEFAULT");
		carAppointmentDetails.setCarId(1);
		carAppointmentDetails.setDeliveryDate(java.sql.Date.valueOf(LocalDate.now()));
    	carAppointmentDetails.setEmailId("DEFAULT");
    	carAppointmentDetails.setModelId("DEFAULT");
    	carAppointmentDetails.setModelName("DEFAULT");
    	carAppointmentDetails.setPrice(0);
    	carAppointmentDetails.setStatus("NEW");
    	carAppointmentDetails.setUserName("DEFAULT");
    	carAppointmentDetails.setAppointmentDate(startDate);
    	carAppointmentDetails.setDeliveryDate(endDate);
    	carAppointmentDetails.setDateOfBirth(dobDate);
    	carAppointmentDetails.setAppointmentId(40);
    	
    	when(carAppointmentService.createNewAppointment(carAppointmentDetails)).thenReturn(carAppointmentDetails);
    	assertNotNull(carAppointmentDetails);
    	
		 mvc.perform(
				 post("/car/appointment/create") 
				 .content(asJsonString(carAppointmentDetails))
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON));
		      	 
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testUpdateStatus() throws Exception {
		
		when(carAppointmentService.appointmentExists(1)).thenReturn(true);
    	
		 mvc.perform(put("/car/appointment/update/1/newstatus/NEW")
				 .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk());		 
				
	}

	@Test
	void testRetrieveAppointment() throws Exception {
		
		 mvc.perform(get("/car/appointment/get/1")
				 .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()); 
					        
	}

	@Test
	void testRetrieveAppointmentList() throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDateInString = "2019-08-20";
    	String endDateInString = "2019-08-22";
    	String dob = "1990-08-22";
    	
    	//convert String to LocalDate
    	LocalDate startLocalDate = LocalDate.parse(startDateInString, formatter);
    	java.sql.Date startDate = java.sql.Date.valueOf(startLocalDate);


    	LocalDate endLocalDate = LocalDate.parse(endDateInString, formatter);
    	java.sql.Date endDate = java.sql.Date.valueOf(endLocalDate);
		
    	LocalDate dobLocalDate = LocalDate.parse(dob, formatter);
    	java.sql.Date dobDate = java.sql.Date.valueOf(dobLocalDate);
    	
    	 when(carAppointmentService.retrieveAppointmentList(startDate, endDate)).thenReturn(Stream.of(new CarAppointmentDetails(1, startDate, endDate, "Jack",
    				300, "NEW", "Pamela", 2, "abc@gmail.com", "FURSTENWALL", dobDate,
    				4, "AUDI", "DUSS1067"), (new CarAppointmentDetails(3, startDate, endDate, "Jackson",
    						300, "NEW", "Angela", 21, "abc123@gmail.com", "FURSTENWALL121", dobDate,
    						5, "AUDI", "DUSS101267"))).collect(Collectors.toList()));		    
    			
   					 
    			mvc.perform(get("/car/appointment/get/from/2019-08-20/to/2019-08-22")
				 .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()); 
	}

}
