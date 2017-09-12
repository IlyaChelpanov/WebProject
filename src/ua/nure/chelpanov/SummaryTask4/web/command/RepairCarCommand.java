package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class RepairCarCommand extends Command {
	private static final Logger LOG = Logger.getLogger(RepairCarCommand.class);
	private static final long serialVersionUID = -3444762582194626245L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		CarsAccessCommand cac = new CarsAccessCommand();
		DBManager manager = DBManager.getInstance();
		Long redactCarID = Long.parseLong(request.getParameter("subject"));
		LOG.trace("Request parameter: carID --> " + redactCarID);
		manager.repairCar(redactCarID);
		LOG.debug("Command finished");
		return cac.execute(request, response);
	}

}
