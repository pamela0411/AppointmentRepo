package com.org.nielsen.exception;

public class AppointmentIdNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentIdNotFoundException(int id) {
		
		super("Appointment Id not found : " + id);
	
	}

}
