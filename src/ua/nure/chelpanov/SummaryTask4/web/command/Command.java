package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public abstract class Command implements Serializable {

	private static final long serialVersionUID = 7319759702876086875L;

	public abstract String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
