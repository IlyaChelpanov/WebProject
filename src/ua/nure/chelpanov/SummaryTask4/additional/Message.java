package ua.nure.chelpanov.SummaryTask4.additional;

import ua.nure.chelpanov.SummaryTask4.entity.Entity;
import ua.nure.chelpanov.SummaryTask4.entity.User;

public class Message extends Entity {

	private static final long serialVersionUID = -7718221691328467466L;

	private String date;
	private long userID;
	private User user;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

}
