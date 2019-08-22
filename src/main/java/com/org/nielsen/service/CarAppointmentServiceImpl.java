package com.org.nielsen.service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.org.nielsen.dao.CarAppointmentDao;
import com.org.nielsen.model.CarAppointmentDetails;

@Service
public class CarAppointmentServiceImpl implements CarAppointmentService{

	private static final Logger logger = LoggerFactory.getLogger(CarAppointmentServiceImpl.class);
	
	@Autowired
	CarAppointmentDao carAppointmentDao;
	
	
	
	/**Service method to check if appointment exists 
	 * @param appointmentId
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean appointmentExists(int appointmentId) throws Exception {
		
		return carAppointmentDao.appointmentExists(appointmentId);
	
	}
	
	
	/**Service method to delete appointment based on appointmentId
	 * @param appointmentId
	 * @return 
	 * @throws Exception
	 */
	@Override
	public void deleteAppointment(int appointmentId) throws Exception {

		carAppointmentDao.deleteAppointment(appointmentId);
		
	}
	
	
	/**Service method to create new appointment 
	 * @param newCarAppointmentDetails
	 * @return CarAppointmentDetails
	 * @throws Exception
	 */
	@Override
	public CarAppointmentDetails createNewAppointment(CarAppointmentDetails newCarAppointmentDetails)
			throws Exception {

		
		CarAppointmentDetails carAppointmentDetails = carAppointmentDao.createNewAppointment(newCarAppointmentDetails);
		
		return carAppointmentDetails;
	}

	
	
	/**Service method to update status of an appointment 
	 * @param appointmentId
	 * @param status
	 * @return 
	 * @throws Exception
	 */
	@Override
	public void updateStatus(int appointmentId, String status) throws Exception {
		
		carAppointmentDao.updateStatus(appointmentId, status);
		
	}

	
	/**Service method to retrieve appointment details of an appointment 
	 * @param appointmentId
	 * @return Optional<CarAppointmentDetails>
	 * @throws Exception
	 */
	@Override
	public Optional<CarAppointmentDetails> retrieveAppointment(int appointmentId) throws Exception {
		
		Optional<CarAppointmentDetails> carAppointmentDetails = carAppointmentDao.retrieveAppointment(appointmentId);
		
		return carAppointmentDetails;
	}

	
	/**Service method to retrieve appointment detailslist between mentioned dates sorted by price of an appointment 
	 * @param startDate
	 * @param endDate
	 * @return List<CarAppointmentDetails>
	 * @throws Exception
	 */
	@Override
	public List<CarAppointmentDetails> retrieveAppointmentList(Date startDate, Date endDate) throws Exception {
		
		List<CarAppointmentDetails> carAppointmentDetails = carAppointmentDao.retrieveAppointmentList(startDate, endDate);
		
		logger.debug("appointmentdetails list size" , carAppointmentDetails.size());
		
		return carAppointmentDetails;
	}

}
