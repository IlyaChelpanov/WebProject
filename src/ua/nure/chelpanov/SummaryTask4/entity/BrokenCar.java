package ua.nure.chelpanov.SummaryTask4.entity;

public class BrokenCar extends Entity {

	private static final long serialVersionUID = -1684337351903381239L;

	private String description;
	private Car car;

	public String getDescription() {
		return description;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BrokenCars [description=" + description + "]";
	}

}
