package ua.nure.chelpanov.SummaryTask4.datebase;

import ua.nure.chelpanov.SummaryTask4.entity.Race;

public enum RaceStatus {
	FREE, ENGAGED, DECLINED;

	public static RaceStatus getRaceStatus(Race race) {
		int raceStatusID = race.getRaceStatusID();
		return RaceStatus.values()[raceStatusID];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
