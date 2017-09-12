package ua.nure.chelpanov.SummaryTask4.exceptions;

public class AppException extends Exception {
	
	private static final long serialVersionUID = -6308380266464858891L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}

