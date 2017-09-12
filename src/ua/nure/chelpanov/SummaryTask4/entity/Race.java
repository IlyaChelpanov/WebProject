package ua.nure.chelpanov.SummaryTask4.entity;

public class Race extends Entity {

	private static final long serialVersionUID = -7076889875727858160L;
	private String date;
	private String startCity;
	private String endCity;
	private String timeOfArrive;
	private int typeOfRace;
	private int raceStatusID;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getTimeOfArrive() {
		return timeOfArrive;
	}

	public void setTimeOfArrive(String timeOfArrive) {
		this.timeOfArrive = timeOfArrive;
	}

	public int getTypeOfRace() {
		return typeOfRace;
	}

	public void setTypeOfRace(int typeOfRace) {
		this.typeOfRace = typeOfRace;
	}

	public int getRaceStatusID() {
		return raceStatusID;
	}

	public void setRaceStatusID(int raceStatusID) {
		this.raceStatusID = raceStatusID;
	}

	@Override
	public String toString() {
		return "Race [date=" + date + ", startCity=" + startCity + ", endCity=" + endCity + ", timeOfArrive="
				+ timeOfArrive + ", typeOfRace=" + typeOfRace + ", raceStatusID=" + raceStatusID + "]";
	}

}
