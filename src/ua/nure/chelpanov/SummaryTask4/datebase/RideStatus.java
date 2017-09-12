package ua.nure.chelpanov.SummaryTask4.datebase;

import ua.nure.chelpanov.SummaryTask4.entity.Ride;

public enum RideStatus {
WAITING_FOR_EXECUTION, PROCESSING, EXECUTED;
	
	public static RideStatus getRideStatus(Ride ride){
		int rideStatusID = ride.getRideStatus();
		return RideStatus.values()[rideStatusID];
	}
	public String getName() {
		return name().toLowerCase();
	}
}
