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

public class UpdateCarCommand extends Command {
	private static final Logger LOG = Logger.getLogger(UpdateCarCommand.class);
	private static final long serialVersionUID = -8374614329950414536L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		CarsAccessCommand cac = new CarsAccessCommand();
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ERROR_PAGE;
		Car car = new Car();
		Long redactCarID = Long.parseLong(request.getParameter("subject"));
		String model = request.getParameter("model");
		LOG.trace("Request parameter: model --> " + model);

		int weight = Integer.parseInt(request.getParameter("weight"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int speed = Integer.parseInt(request.getParameter("speed"));
		String descrip = request.getParameter("description");
		LOG.trace("Request parameter: description --> " + descrip);
		if (!descrip.isEmpty()) {
			car.setCarCondition("broken");
			manager.setBrokenCar(redactCarID, descrip);
		}
		if (model.length() < 30 || weight < 1000 || capacity < 150 || speed < 300) {
			car.setId(redactCarID);
			car.setModel(model);
			car.setMaxWeight(weight);
			car.setMaxCapacity(capacity);
			car.setMaxSpeed(speed);
			manager.updateCarByID(car);
			forward = cac.execute(request, response);
		} else {
			throw new AppException("Cannot update car with such characteristics");
		}
		LOG.debug("Command finished");
		return forward;
	}

}
