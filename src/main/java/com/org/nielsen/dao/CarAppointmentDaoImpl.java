package com.org.nielsen.dao;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.org.nielsen.model.CarAppointmentDetails;

@Transactional
@Repository
public class CarAppointmentDaoImpl implements CarAppointmentDao {

	private static final Logger logger = LoggerFactory.getLogger(CarAppointmentDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Dao method to delete appointment based on appointmentId
	 * 
	 * @param appointmentId
	 * @return
	 * @throws Exception
	 */
	public void deleteAppointment(int appointmentId) throws Exception {

		String sql = "DELETE FROM car_appointment_details WHERE ticket_id=?";
		jdbcTemplate.update(sql, appointmentId);

	}

	/**
	 * Dao method to check if user exists
	 * 
	 * @param newCarAppointmentDetails
	 * @return boolean
	 * @throws Exception
	 */
	private boolean userExists(CarAppointmentDetails newCarAppointmentDetails) {

		String sql = "SELECT count(*) FROM user_details WHERE user_id = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, newCarAppointmentDetails.getUserId());
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Dao method to check if appointment details exists
	 * 
	 * @param appointmentId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean appointmentExists(int appointmentId) throws Exception {

		String sql = "SELECT count(*) FROM car_appointment_details WHERE ticket_id = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, appointmentId);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Dao method to create new appointment
	 * 
	 * @param newCarAppointmentDetails
	 * @return CarAppointmentDetails
	 * @throws Exception
	 */
	public CarAppointmentDetails createNewAppointment(CarAppointmentDetails newCarAppointmentDetails) throws Exception {

		if (userExists(newCarAppointmentDetails)) {

			String sql = "INSERT INTO car_appointment_details (appointment_date, delivery_date, assigned_to, price, status, user_id, car_id) values (?, ?, ?, ?, ?, ?, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "ticket_id" });
				ps.setDate(1, newCarAppointmentDetails.getAppointmentDate());
				ps.setDate(2, newCarAppointmentDetails.getDeliveryDate());
				ps.setString(3, newCarAppointmentDetails.getAssignedTo());
				ps.setInt(4, newCarAppointmentDetails.getPrice());
				ps.setString(5, newCarAppointmentDetails.getStatus());
				ps.setInt(6, newCarAppointmentDetails.getUserId());
				ps.setInt(7, newCarAppointmentDetails.getCarId());
				return ps;
			}, keyHolder);

			final int newAppointmentId = keyHolder.getKey().intValue();

			newCarAppointmentDetails.setAppointmentId(newAppointmentId);
		} else
			newCarAppointmentDetails.setAppointmentId(0);
		return newCarAppointmentDetails;

	}

	/**
	 * Dao method to update status of an appointment
	 * 
	 * @param appointmentId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public void updateStatus(int appointmentId, String status) throws Exception {

		String sql = "UPDATE car_appointment_details SET status=? WHERE ticket_id=?";
		jdbcTemplate.update(sql, status, appointmentId);

	}

	/**
	 * Dao method to retrieve appointment details of an appointmentId
	 * 
	 * @param appointmentId
	 * @return Optional<CarAppointmentDetails>
	 * @throws Exception
	 */
	public Optional<CarAppointmentDetails> retrieveAppointment(int appointmentId) throws Exception {

		return namedParameterJdbcTemplate.queryForObject("select appointmnt.ticket_id, appointmnt.appointment_date,  "
				+ "appointmnt.delivery_date, appointmnt.assigned_to, appointmnt.price, appointmnt.status, "
				+ "usr.username, usr.user_id, usr.email, usr.address, usr.date_of_birth, car.car_id, car.model_name, car.model_id "
				+ "from car_appointment_details appointmnt " + "left join user_details usr "
				+ "on appointmnt.user_id = usr.user_id " + "left join car_details car "
				+ "on car.user_id = usr.user_id " + "and car.car_id = appointmnt.car_id "
				+ "where appointmnt.ticket_id = :ticket_id", new MapSqlParameterSource("ticket_id", appointmentId),
				(rs, rowNum) -> Optional.of(new CarAppointmentDetails(rs.getInt("ticket_id"),
						rs.getDate("appointment_date"), rs.getDate("delivery_date"), rs.getString("assigned_to"),
						rs.getInt("price"), rs.getString("status"), rs.getString("username"), rs.getInt("user_id"),
						rs.getString("email"), rs.getString("address"), rs.getDate("date_of_birth"),
						rs.getInt("car_id"), rs.getString("model_name"), rs.getString("model_id")

				)));
	}

	/**
	 * Dao method to retrieve list of appointment details between mentioned dates
	 * sorted by price
	 * 
	 * @param startDate
	 * @param endDate
	 * @return List<CarAppointmentDetails>
	 * @throws Exception
	 */
	public List<CarAppointmentDetails> retrieveAppointmentList(Date startDate, Date endDate) throws Exception {

		String sql = "select appointmnt.ticket_id, appointmnt.appointment_date,  "
				+ "appointmnt.delivery_date, appointmnt.assigned_to, appointmnt.price, appointmnt.status, "
				+ "usr.username, usr.user_id, usr.email, usr.address, usr.date_of_birth, car.car_id, car.model_name, car.model_id "
				+ "from car_appointment_details appointmnt " + "left join user_details usr "
				+ "on appointmnt.user_id = usr.user_id " + "left join car_details car "
				+ "on car.user_id = usr.user_id " + "and car.car_id = appointmnt.car_id "
				+ "where appointmnt.appointment_date between '" + startDate + "' and '" + endDate + "' order by price";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new CarAppointmentDetails(

				rs.getInt("ticket_id"), rs.getDate("appointment_date"), rs.getDate("delivery_date"),
				rs.getString("assigned_to"), rs.getInt("price"), rs.getString("status"), rs.getString("username"),
				rs.getInt("user_id"), rs.getString("email"), rs.getString("address"), rs.getDate("date_of_birth"),
				rs.getInt("car_id"), rs.getString("model_name"), rs.getString("model_id")

		));

	}

}
