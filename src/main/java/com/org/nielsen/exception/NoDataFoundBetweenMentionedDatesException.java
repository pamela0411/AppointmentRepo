package com.org.nielsen.exception;

import java.sql.Date;

public class NoDataFoundBetweenMentionedDatesException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataFoundBetweenMentionedDatesException(Date startDate, Date endDate) {
		
		super("No appointments found between dates " + startDate +" and " + endDate);
	
	}
 

}
