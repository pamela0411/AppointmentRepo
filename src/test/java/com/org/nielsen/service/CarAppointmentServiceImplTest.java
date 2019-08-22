package com.org.nielsen.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.org.nielsen.dao.CarAppointmentDaoImpl;
import com.org.nielsen.model.CarAppointmentDetails;


class CarAppointmentServiceImplTest {

	@InjectMocks
    CarAppointmentServiceImpl carAppointmentServiceImpl;
	
	@Mock
	CarAppointmentDaoImpl carAppointmentDaoImpl;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testDeleteAppointment() throws Exception {
		
    	carAppointmentServiceImpl.deleteAppointment(1);
    	verify(carAppointmentDaoImpl, times(1)).deleteAppointment(1);
    	
	}

	@Test
	void testCreateNewAppointment() throws Exception {
		
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
    	carAppointmentDetails.setAppointmentId(40);
		
		when(carAppointmentDaoImpl.createNewAppointment(carAppointmentDetails)).thenReturn(carAppointmentDetails);
		
		CarAppointmentDetails carAppointmentDetailsTest = carAppointmentServiceImpl.createNewAppointment(carAppointmentDetails);
		
		assertNotNull(carAppointmentDetailsTest);
		
		assertEquals("John", carAppointmentDetailsTest.getAssignedTo());
	}

	
	 @Test void testUpdateStatus() throws Exception {
	 
		 carAppointmentServiceImpl.updateStatus(1, "NEW");
	    	verify(carAppointmentDaoImpl, times(1)).updateStatus(1,"NEW");
	 }
	 
	 

	@Test
	void testRetrieveAppointment() throws Exception {
		
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
    	carAppointmentDetails.setAppointmentId(40);
		
		when(carAppointmentDaoImpl.retrieveAppointment(40)).thenReturn(Optional.of(carAppointmentDetails));
		
		Optional<CarAppointmentDetails> carAppointmentDetailsTest = carAppointmentServiceImpl.retrieveAppointment(40);
		
		assertTrue(carAppointmentDetailsTest.isPresent());
		
		assertEquals("John", carAppointmentDetailsTest.get().getAssignedTo());
		
	}

	@Test
	void testRetrieveAppointmentList() throws Exception {
    	    	
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
    	
        when(carAppointmentDaoImpl.retrieveAppointmentList(startDate, endDate)).thenReturn(Stream.of(new CarAppointmentDetails(1, startDate, endDate, "Jack",
			300, "NEW", "Pamela", 2, "abc@gmail.com", "FURSTENWALL", dobDate,
			4, "AUDI", "DUSS1067"), (new CarAppointmentDetails(3, startDate, endDate, "Jackson",
					300, "NEW", "Angela", 21, "abc123@gmail.com", "FURSTENWALL121", dobDate,
					5, "AUDI", "DUSS101267"))).collect(Collectors.toList()));		    
		
		assertEquals(2, carAppointmentServiceImpl.retrieveAppointmentList(startDate, endDate).size());
	}

}
