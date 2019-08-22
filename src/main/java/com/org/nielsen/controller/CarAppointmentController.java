package com.org.nielsen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.nielsen.exception.AppointmentIdNotFoundException;
import com.org.nielsen.exception.NoDataFoundBetweenMentionedDatesException;
import com.org.nielsen.model.CarAppointmentDetails;
import com.org.nielsen.service.CarAppointmentService;

@RestController
@RequestMapping("/car")
public class CarAppointmentController {

	private static final Logger logger = LoggerFactory.getLogger(CarAppointmentController.class);

	@Autowired
	CarAppointmentService carAppointmentService;

	
	
	/**
	 * Controller method to delete appointment based on appointmentId
	 * 
	 * @param appointmentId
	 * @return
	 * @throws Exception
	 */
	
	@DeleteMapping(path = "/appointment/delete/{appointmentId}", produces = "application/json")
	public ResponseEntity<String> deleteAppointment(@PathVariable(value = "appointmentId") int appointmentId)
			throws Exception {

		logger.info("Inside controller method deleteAppointment");

		// delete only if appointment details exists
		if (!carAppointmentService.appointmentExists(appointmentId)) {

			throw new AppointmentIdNotFoundException(appointmentId);
		}
		carAppointmentService.deleteAppointment(appointmentId);

		return new ResponseEntity("Appointment deleted", HttpStatus.OK);

	}

	/**
	 * Controller method to create new appointment
	 * 
	 * @param newCarAppointmentDetails
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/appointment/create", produces = "application/json")
	public ResponseEntity createNewAppointment(@Valid @RequestBody CarAppointmentDetails newCarAppointmentDetails)
			throws Exception {

		logger.info("Inside controller method createNewAppointment");

		CarAppointmentDetails carApponitmentDetails = carAppointmentService
				.createNewAppointment(newCarAppointmentDetails);

		if (0 != carApponitmentDetails.getAppointmentId()) {

			return new ResponseEntity(carApponitmentDetails, HttpStatus.CREATED);

		} else

			return new ResponseEntity("User not available. Please add user details first", HttpStatus.OK);
	}

	/**
	 * Controller method to update the status of an appointment with the new status
	 * 
	 * @param appointmentId
	 * @param newStatus
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/appointment/update/{appointmentId}/newstatus/{newStatus:[a-zA-Z &+-]*}", produces = "application/json")
	public ResponseEntity<String> updateStatus(@PathVariable(value = "appointmentId") int appointmentId,
			@PathVariable(value = "newStatus") String newStatus) throws Exception {

		logger.info("Inside controller method updateStatus");

		// update only if appointment details exists
		if (!carAppointmentService.appointmentExists(appointmentId)) {
           
			throw new AppointmentIdNotFoundException(appointmentId);
		}
		carAppointmentService.updateStatus(appointmentId, newStatus);

		return new ResponseEntity("Status updated", HttpStatus.OK);

	}

	/**
	 * Controller method to retrieve appointment details of a particular
	 * appointmentId
	 * 
	 * @param appointmentId
	 * @return
	 * @throws Exception
	 */
	
	@GetMapping(value = "/appointment/get/{appointmentId}", produces = "application/json")
	public ResponseEntity<String> retrieveAppointment(@PathVariable(value = "appointmentId") int appointmentId)
			throws Exception {

		logger.info("Inside controller method retrieveAppointment");

		Optional<CarAppointmentDetails> carAppointmentDetails = null;
		try {
			
			carAppointmentDetails = carAppointmentService.retrieveAppointment(appointmentId);
		
		} catch (Exception ex) {

			throw new AppointmentIdNotFoundException(appointmentId);
		}

		return new ResponseEntity(carAppointmentDetails, HttpStatus.OK);
	}

	/**
	 * Controller method to retrieve appointments list between two mentioned dates
	 * sorted by price.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/appointment/get/from/{startDate}/to/{endDate}", produces = "application/json")
	public ResponseEntity<String> retrieveAppointmentList(
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "startDate") LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "endDate") LocalDate endDate)
			throws Exception {

		logger.info("Inside controller method retrieveAppointmentList");

		// convert date from LocalDate to java.sql.Date
		java.sql.Date startDateinSql = java.sql.Date.valueOf(startDate);
		java.sql.Date endDateinSql = java.sql.Date.valueOf(endDate);

		List<CarAppointmentDetails> carAppointmentDetails = carAppointmentService
				.retrieveAppointmentList(startDateinSql, endDateinSql);

		if (carAppointmentDetails.size() == 0)

			throw new NoDataFoundBetweenMentionedDatesException(startDateinSql, endDateinSql);

		return new ResponseEntity(carAppointmentDetails, HttpStatus.OK);
	}

}
