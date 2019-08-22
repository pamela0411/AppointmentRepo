package com.org.nielsen.model;

import java.sql.Date;


public class CarAppointmentDetails {
	
	private int appointmentId ;
	private Date appointmentDate ;
	private Date deliveryDate ;
	private String assignedTo;
	private int price;
	private String status;
    private String userName;
    private int userId;
    private String emailId;   
    private String address;
    private Date dateOfBirth;
    private int carId;
    private String modelName;
	private String modelId;
	
	public CarAppointmentDetails() {
		
	}
	
	public CarAppointmentDetails(int appointmentId, Date appointmentDate, Date deliveryDate, String assignedTo,
			int price, String status, String userName, int userId, String emailId, String address, Date dateOfBirth,
			int carId, String modelName, String modelId) {
		super();
		this.appointmentId = appointmentId;
		this.setAppointmentDate(appointmentDate);
		this.setDeliveryDate(deliveryDate);
		this.assignedTo = assignedTo;
		this.price = price;
		this.status = status;
		this.userName = userName;
		this.userId = userId;
		this.emailId = emailId;
		this.address = address;
		this.setDateOfBirth(dateOfBirth);
		this.carId = carId;
		this.modelName = modelName;
		this.modelId = modelId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
    

}
