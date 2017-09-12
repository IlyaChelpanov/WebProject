package ua.nure.chelpanov.SummaryTask4.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import ua.nure.chelpanov.SummaryTask4.additional.Message;
import ua.nure.chelpanov.SummaryTask4.entity.BrokenCar;
import ua.nure.chelpanov.SummaryTask4.entity.Car;
import ua.nure.chelpanov.SummaryTask4.entity.Order;
import ua.nure.chelpanov.SummaryTask4.entity.Race;
import ua.nure.chelpanov.SummaryTask4.entity.Ride;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.DBException;
import ua.nure.chelpanov.SummaryTask4.exceptions.Messages;

public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static DBManager instance;

	/*
	 * Using singleton in order to obtain pool system connection
	 */
	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/SummaryTask4");

		} catch (NamingException ex) {
			// log
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	private DataSource ds;

	private static final String SQL_FIND_USER_BY_USERNAME = "select * from users where Username = ?";
	private static final String SQL_FIND_ALL_USERS = "select * from users";
	private static final String SQL_ADD_NEW_USER = "insert into users values(default, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_USER = "update users set Username=?, FullName = ?, Pass = ?, RoleID = ?, email = ? where Username = ?";
	private static final String SQL_FIND_ALL_RACES = "select * from races";
	private static final String SQL_FIND_ALL_CARS = "select * from cars";
	private static final String SQL_FIND_ALL_ORDERS = "select*from orders";
	private static final String SQL_DELETE_USER = "delete from users where username = ?";
	private static final String SQL_ADD_NEW_RACE = "insert into races values(default, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_CHANGE_RACE_STATUS = "update races set RaceStatusID = ? where RaceNumber = ?";
	private static final String SQL_DELETE_ORDER = "delete from orders where NumberOfRace = ?";
	private static final String SQL_DELETE_RIDE = "delete from ride where RaceNumber = ?";
	private static final String SQL_FIND_RACE_BY_ID = "select*from races where RaceNumber = ?";
	private static final String SQL_UPDATE_RACE = "update Races set DateOfRide = ?, StartCity = ?,"
			+ "EndCity= ?, TimeOfArrive= ?, TypeOfRace= ?, RaceStatusID= ? where RaceNumber = ?";
	private static final String SQL_MAKE_AN_ORDER = "insert into orders values(default, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_USERS_ORDERS = "select*from orders where DriverID = ?";
	private static final String SQL_GET_ORDER_BY_ID = "select*from orders where ID = ?";
	private static final String SQL_CHANGE_ORDER_STATUS = "update orders set OrderStatusID = ? where ID = ?";
	private static final String SQL_ADD_NEW_RIDE = "insert into ride values(default, ?, ?, ?, ?)";
	private static final String SQL_FIND_ALL_RIDES = "select*from ride";
	private static final String SQL_FIND_CAR_BY_ID = "select*from cars where CarNumber = ?";
	private static final String SQL_FIND_USER_BY_ID = "select*from users where ID = ?";
	private static final String SQL_FIND_RIDE_BY_ID = "select*from ride where ID=?";
	private static final String SQL_EXECUTE_RIDE = "update Ride set RideStatus = ? where ID=?";
	private static final String SQL_ADD_BROKEN_CAR = "insert into brokencars values(?, ?)";
	private static final String SQL_ADD_NEW_CAR = "insert into cars value(default, ?, ?, ?, ?, default)";
	private static final String SQL_DELETE_CAR = "delete from Cars where CarNumber = ?";
	private static final String SQL_UPDATE_CAR = "update cars set Model = ?, MaxWeight = ?, MaxCapacity = ?, "
			+ "MaxSpeed = ? where CarNumber = ?";

	private static final String SQL_SET_BROKEN_CAR = "update cars set CarCondition = ? where CarNumber = ?";

	private static final String SQL_DELETE_BROKEN_CAR = "delete from brokencars where CarNumber = ?";

	private static final String SQL_FIND_ALL_BROKEN_CARS = "select*from BrokenCars";

	private static final String SQL_FIND_ALL_MESSAGES = "select*from chat";

	private static final String SQL_ADD_NEW_MESSAGE = "insert into chat values(default, ?, ?, ?)";

	private static final String SQL_DELETE_MESSAGE = "delete from chat where ID = ?";

	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	public User getUserByUsername(String username) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_USERNAME);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setUsername(rs.getString(Fields.USERNAME));
		user.setPassword(rs.getString(Fields.PASSWORD));
		user.setFullName(rs.getString(Fields.FULL_NAME));
		user.setEmail(rs.getString(Fields.EMAIL));
		user.setRoleID(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}

	public List<User> getAllUsers() throws DBException {
		List<User> users = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return users;
	}

	public void addNewUser(User user) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_USER);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFullName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setInt(5, user.getRoleID());
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}
	}

	public void updateUserByUsername(User redacted, String username) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			pstmt.setString(1, redacted.getUsername());
			pstmt.setString(2, redacted.getFullName());
			pstmt.setString(3, redacted.getPassword());
			pstmt.setInt(4, redacted.getRoleID());
			pstmt.setString(5, redacted.getEmail());
			pstmt.setString(6, username);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public void deleteUser(String userlog) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_USER);
			pstmt.setString(1, userlog);
			pstmt.execute();
			con.commit();
		} catch (

		SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	public List<Race> getAllRaces() throws DBException {
		List<Race> races = new ArrayList<Race>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_RACES);
			while (rs.next()) {
				races.add(extractRace(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RACES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return races;
	}

	private Race extractRace(ResultSet rs) throws SQLException {
		Race race = new Race();
		race.setId(rs.getLong("RaceNumber"));
		race.setDate(rs.getString(Fields.DATE_OF_RIDE));
		race.setStartCity(rs.getString(Fields.START_CITY));
		race.setEndCity(rs.getString(Fields.END_CITY));
		race.setTimeOfArrive(rs.getString(Fields.TIME_OF_ARRIVE));
		race.setTypeOfRace(rs.getInt("TypeOfRace"));
		race.setRaceStatusID(rs.getInt("RaceStatusID"));
		return race;
	}

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 */
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	public void addNewRace(Race race) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_RACE);
			pstmt.setString(1, race.getDate());
			pstmt.setString(2, race.getStartCity());
			pstmt.setString(3, race.getEndCity());
			pstmt.setString(4, race.getTimeOfArrive());
			pstmt.setInt(5, race.getTypeOfRace());
			pstmt.setInt(6, 1);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_RACE, ex);
		} finally {
			close(con, pstmt, rs);
		}
	}

	public void deleteRace(int raceID) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_ORDER);

			pstmt.setInt(1, raceID);
			pstmt.execute();
			pstmt2 = con.prepareStatement(SQL_DELETE_RIDE);
			pstmt2.setInt(1, raceID);
			pstmt2.execute();
			pstmt3 = con.prepareStatement(SQL_CHANGE_RACE_STATUS);
			pstmt3.setInt(1, 3);
			pstmt3.setInt(2, raceID);
			pstmt3.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_RACE, ex);
		} finally {
			close(con);
			close(pstmt);
			close(pstmt2);
			close(pstmt3);
		}
	}

	public Race getRaceByID(long raceID) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Race race = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_RACE_BY_ID);
			pstmt.setLong(1, raceID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				race = extractRace(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RACE, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return race;
	}

	public void updateRaceByID(Race newRace, Long redactRaceID) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_RACE);
			pstmt.setString(1, newRace.getDate());
			pstmt.setString(2, newRace.getStartCity());
			pstmt.setString(3, newRace.getEndCity());
			pstmt.setString(4, newRace.getTimeOfArrive());
			pstmt.setInt(5, newRace.getTypeOfRace());
			pstmt.setInt(6, newRace.getRaceStatusID());
			pstmt.setLong(7, redactRaceID);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_RACE, ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public void makeAnOrder(int weight, int capacity, int speed, Long idUser, Long idRace) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_MAKE_AN_ORDER);
			pstmt.setLong(1, idRace);
			pstmt.setLong(2, idUser);
			pstmt.setInt(3, weight);
			pstmt.setInt(4, capacity);
			pstmt.setInt(5, speed);
			pstmt.setInt(6, 2);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_MAKE_ORDER, ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public List<Order> gerUserOrders(Long id) throws DBException {
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_USERS_ORDERS);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return orders;
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("ID"));
		order.setRaceID((int) rs.getLong(Fields.NUMBER_OF_RACE));
		order.setDriverID((int) rs.getLong(Fields.DRIVER_ID));
		order.setRequiredWeight(rs.getInt(Fields.REQUIRED_WEIGHT));
		order.setRequiredCapacity(rs.getInt(Fields.REQUIRED_CAPACITY));
		order.setRequireSpeed(rs.getInt(Fields.REQUIRED_SPEED));
		order.setOrderStatusID((int) rs.getLong("OrderStatusID"));
		return order;
	}

	public List<Order> getAllOrders() throws DBException {
		List<Order> orders = new ArrayList<Order>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ORDERS);
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return orders;
	}

	public Order getOrderByID(Long orderID) throws DBException {
		Order order = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_ORDER_BY_ID);
			pstmt.setLong(1, orderID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = extractOrder(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return order;

	}

	public void denyOrder(Long long1) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CHANGE_ORDER_STATUS);
			pstmt.setInt(1, 3);
			pstmt.setLong(2, long1);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER, ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public void acceptOrder(Long id, String carID, long driverID, long raceID) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CHANGE_ORDER_STATUS);
			pstmt.setInt(1, 1);
			pstmt.setLong(2, id);
			pstmt.execute();
			pstmt2 = con.prepareStatement(SQL_ADD_NEW_RIDE);
			pstmt2.setLong(1, raceID);
			pstmt2.setLong(2, driverID);
			pstmt2.setLong(3, Long.parseLong(carID));
			pstmt2.setLong(4, 1);
			pstmt2.execute();
			pstmt3 = con.prepareStatement(SQL_CHANGE_RACE_STATUS);
			pstmt3.setLong(1, 2);
			pstmt3.setLong(2, raceID);
			pstmt3.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER, ex);
		} finally {
			close(pstmt2);
			close(pstmt3);
			close(con, pstmt, rs);
		}

	}

	public List<Car> getAllCars() throws DBException {
		List<Car> cars = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_CARS);
			while (rs.next()) {
				cars.add(extractCar(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return cars;
	}

	private Car extractCar(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getLong("CarNumber"));
		car.setModel(rs.getString(Fields.CAR_MODEL));
		car.setMaxWeight(rs.getInt(Fields.MAX_WEIGHT));
		car.setMaxCapacity(rs.getInt(Fields.MAX_CAPACITY));
		car.setMaxSpeed(rs.getInt(Fields.MAX_SPEED));
		car.setCarCondition(rs.getString(Fields.CAR_CONDITION));
		return car;
	}

	public List<Ride> getAllRides() throws DBException {
		List<Ride> rides = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_RIDES);
			while (rs.next()) {
				rides.add(extractRide(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RIDES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return rides;
	}

	private Ride extractRide(ResultSet rs) throws SQLException {
		Ride ride = new Ride();
		ride.setId(rs.getLong("ID"));
		ride.setRaceNumberID((int) rs.getLong(Fields.RACE_NUMBER));
		ride.setCarNumber((int) rs.getLong(Fields.CAR_NUMBER));
		ride.setDriverID((int) rs.getLong(Fields.DRIVER_ID));
		ride.setRideStatus((int) rs.getLong(Fields.RIDE_STATUS_ID));
		;
		return ride;
	}

	public Car getCarByID(Long carID) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Car car = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CAR_BY_ID);
			pstmt.setLong(1, carID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				car = extractCar(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return car;
	}

	public User getUserByID(int driverID) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		User user = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, driverID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	public Ride getRideByID(Long rideID) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Ride ride = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_RIDE_BY_ID);
			pstmt.setLong(1, rideID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ride = extractRide(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RIDE, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return ride;
	}

	public void executeRide(Long rideID) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_EXECUTE_RIDE);
			pstmt.setLong(1, 2);
			pstmt.setLong(2, rideID);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RIDE, ex);
		} finally {
			close(con);
			close(pstmt);
		}

	}

	public void setBrokenCar(Long redactCarID, String description) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_BROKEN_CAR);
			pstmt.setLong(1, redactCarID);
			pstmt.setString(2, description);
			pstmt.execute();
			pstmt2 = con.prepareStatement(SQL_SET_BROKEN_CAR);
			pstmt2.setString(1, "broken");
			pstmt2.setLong(2, redactCarID);
			pstmt2.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_CAR, ex);
		} finally {
			close(con);
			close(pstmt);
			close(pstmt2);
		}

	}

	public void addNewCar(Car car) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_CAR);
			pstmt.setString(1, car.getModel());
			pstmt.setInt(2, car.getMaxWeight());
			pstmt.setInt(3, car.getMaxCapacity());
			pstmt.setInt(4, car.getMaxSpeed());
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_ADD_CAR, ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public void deleteCar(Long carID) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt2 = con.prepareStatement(SQL_DELETE_BROKEN_CAR);
			pstmt2.setLong(1, carID);
			pstmt2.execute();
			pstmt = con.prepareStatement(SQL_DELETE_CAR);
			pstmt.setLong(1, carID);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_CAR, ex);
		} finally {
			close(con);
			close(pstmt);
			close(pstmt2);
		}
	}

	public void updateCarByID(Car car) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_CAR);
			pstmt.setString(1, car.getModel());
			pstmt.setInt(2, car.getMaxWeight());
			pstmt.setInt(3, car.getMaxCapacity());
			pstmt.setInt(4, car.getMaxSpeed());
			pstmt.setLong(5, car.getID());
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_CAR, ex);
		} finally {
			close(con);
			close(pstmt);
		}

	}

	public void repairCar(Long redactCarID) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_BROKEN_CAR);
			pstmt.setLong(1, redactCarID);
			pstmt.execute();
			pstmt2 = con.prepareStatement(SQL_SET_BROKEN_CAR);
			pstmt2.setString(1, "working");
			pstmt2.setLong(2, redactCarID);
			pstmt2.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_CAR, ex);
		} finally {
			close(con);
			close(pstmt);
			close(pstmt2);
		}

	}

	public List<BrokenCar> getAllBrokenCars() throws DBException {
		List<BrokenCar> bcars = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_BROKEN_CARS);
			while (rs.next()) {
				bcars.add(extractBrokenCar(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return bcars;
	}

	private BrokenCar extractBrokenCar(ResultSet rs) throws SQLException {
		BrokenCar bcar = new BrokenCar();
		bcar.setId(rs.getLong("CarNumber"));
		bcar.setDescription(rs.getString("DescriptionOfBreakage"));
		return bcar;
	}

	public List<Message> getAllMessages() throws DBException {
		List<Message> messages = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_MESSAGES);
			while (rs.next()) {
				messages.add(extractMessage(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_MESSAGES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return messages;
	}

	private Message extractMessage(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getLong("ID"));
		message.setUserID(rs.getLong("UserID"));
		message.setDate(rs.getString("Date"));
		message.setMessage(rs.getString("Message"));
		return message;
	}

	public void addNewMessage(String context, User user, String sdate) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_ADD_NEW_MESSAGE);
			pstmt.setLong(1, user.getID());
			pstmt.setString(2, sdate);
			pstmt.setString(3, context);
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_SEND_MESSAGE, ex);
		} finally {
			close(con);
			close(pstmt);
		}

	}

	public void deleteMessage(Message message) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_MESSAGE);
			pstmt.setLong(1, message.getID());
			pstmt.execute();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_MESSAGE, ex);
		} finally {
			close(con);
			close(pstmt);
		}

	}
}
