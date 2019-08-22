package com.org.nielsen.service;

import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;

import com.org.nielsen.model.CarAppointmentDetails;

public interface CarAppointmentService {

	public void deleteAppointment(int appointmentId) throws Exception;

	public CarAppointmentDetails createNewAppointment(CarAppointmentDetails newCarAppointmentDetails) throws Exception;
	
	public void updateStatus(int appointmentId, String status) throws Exception;
	
	public Optional<CarAppointmentDetails> retrieveAppointment(int appointmentId) throws Exception;
	
	public List<CarAppointmentDetails> retrieveAppointmentList(Date startDate, Date endDate) throws Exception;
	
	public boolean appointmentExists(int appointmentId) throws Exception;

}
