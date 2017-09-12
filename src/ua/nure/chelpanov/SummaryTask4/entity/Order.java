package ua.nure.chelpanov.SummaryTask4.entity;

public class Order extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4938282892530956417L;

	private int raceID;
	private int driverID;
	private int requiredWeight;
	private int requiredCapacity;
	private int requireSpeed;
	private int orderStatusID;
	private Race race;

	public int getRaceID() {
		return raceID;
	}

	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public int getRequiredWeight() {
		return requiredWeight;
	}

	public void setRequiredWeight(int requiredWeight) {
		this.requiredWeight = requiredWeight;
	}

	public int getRequiredCapacity() {
		return requiredCapacity;
	}

	public void setRequiredCapacity(int requiredCapacity) {
		this.requiredCapacity = requiredCapacity;
	}

	public int getRequireSpeed() {
		return requireSpeed;
	}

	public void setRequireSpeed(int requireSpeed) {
		this.requireSpeed = requireSpeed;
	}

	public int getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(int orderStatusID) {
		this.orderStatusID = orderStatusID;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	@Override
	public String toString() {
		return "Order [raceID=" + raceID + ", driverID=" + driverID + ", requiredWeight=" + requiredWeight
				+ ", requiredCapacity=" + requiredCapacity + ", requireSpeed=" + requireSpeed + ", orderStatusID="
				+ orderStatusID + "]";
	}

	

}
