package ua.nure.chelpanov.SummaryTask4.entity;

public class Ride extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3316086429194889066L;

	private int raceNumberID;
	private int driverID;
	private int carNumber;
	private int rideStatus;
	private Race race;
	private User user;
	private Car car;

	public int getRaceNumberID() {
		return raceNumberID;
	}

	public void setRaceNumberID(int raceNumberID) {
		this.raceNumberID = raceNumberID;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public int getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}

	public int getRideStatus() {

		return rideStatus;
	}

	public void setRideStatus(int rideStatus) {
		this.rideStatus = rideStatus;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Ride [raceNumberID=" + raceNumberID + ", driverID=" + driverID + ", carNumber=" + carNumber
				+ ", rideStatus=" + rideStatus + "]";
	}

}
