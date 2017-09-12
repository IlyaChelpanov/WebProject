package ua.nure.chelpanov.SummaryTask4.entity;

public class Car extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823924186336146365L;

	private String model;
	private int maxWeight;
	private int maxCapacity;
	private int maxSpeed;
	private String carCondition;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getCarCondition() {
		return carCondition;
	}

	public void setCarCondition(String carCondition) {
		this.carCondition = carCondition;
	}

	@Override
	public String toString() {
		return "Order [model=" + model + ", maxWeight=" + maxWeight + ", maxCapacity=" + maxCapacity + ", maxSpeed="
				+ maxSpeed + ", carCondition=" + carCondition + "]";
	}
}
