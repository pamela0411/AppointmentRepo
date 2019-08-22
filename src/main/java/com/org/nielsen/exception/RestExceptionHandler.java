package com.org.nielsen.exception;

import java.io.IOException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.toString());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationException(Exception ex) throws IOException {
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.toString());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);   	
    }
    
    @ExceptionHandler(AppointmentIdNotFoundException.class) 
    public ResponseEntity<ErrorResponse> customHandleNotFound(Exception ex) throws Exception {
    	
    	ErrorResponse errors = new ErrorResponse();      
        errors.setMessage(ex.getMessage());
        errors.setErrorCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    	   
    }
    
    @ExceptionHandler(NoDataFoundBetweenMentionedDatesException.class) 
    public ResponseEntity<ErrorResponse> customDataNotFound(Exception ex) throws Exception {
    	
    	ErrorResponse errors = new ErrorResponse();      
        errors.setMessage(ex.getMessage());
        errors.setErrorCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    	   
    }
}
