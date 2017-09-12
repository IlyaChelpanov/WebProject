package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Car;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class CarsAccessCommand extends Command {
	private static final Logger LOG = Logger.getLogger(CarsAccessCommand.class);
	private static final long serialVersionUID = 4428424068473144817L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Car> cars = manager.getAllCars();
		LOG.trace("Found in DB --> " + cars);
		session.setAttribute("cars", cars);
		LOG.debug("Command finished");
		return Path.PAGE_CARS;
	}

}
