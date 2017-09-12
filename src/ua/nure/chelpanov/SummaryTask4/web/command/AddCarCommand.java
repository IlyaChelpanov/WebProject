package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Car;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class AddCarCommand extends Command {
	private static final Logger LOG = Logger.getLogger(AddCarCommand.class);
	private static final long serialVersionUID = -2893211486859574195L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		CarsAccessCommand cac = new CarsAccessCommand();
		String forward = Path.PAGE_ERROR_PAGE;
		DBManager manager = DBManager.getInstance();
		Car car = new Car();
		String model = request.getParameter("Model");
		LOG.trace("Request parameter: addcar --> " + model);
		int weight;
		int capacity;
		int speed;
		try {
			weight = Integer.parseInt(request.getParameter("weight"));
			capacity = Integer.parseInt(request.getParameter("capacity"));
			speed = Integer.parseInt(request.getParameter("speed"));
		} catch (NumberFormatException ex) {
			throw new AppException("Values are not numbers!");
		}
		if (model.length() > 30 || weight > 1000 || capacity > 150 || speed > 300) {
			throw new AppException("You cannot add car with such characteristics!");
		} else {
			car.setModel(model);
			car.setMaxWeight(weight);
			car.setMaxCapacity(capacity);
			car.setMaxSpeed(speed);
			manager.addNewCar(car);
			forward = cac.execute(request, response);
			LOG.trace("Adding new car --> " + car);
		}
		LOG.debug("Command finished");
		return forward;
	}

}
