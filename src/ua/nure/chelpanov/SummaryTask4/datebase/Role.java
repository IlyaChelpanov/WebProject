package ua.nure.chelpanov.SummaryTask4.datebase;

import ua.nure.chelpanov.SummaryTask4.entity.User;

public enum Role {
	ADMINISTRATOR, DISPATCHER, DRIVER;

	public static Role getRole(User user) {
		int roleID = user.getRoleID();
		return Role.values()[roleID-1];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
