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
import ua.nure.chelpanov.SummaryTask4.entity.Ride;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class RedactCarCommand extends Command {
	private static final Logger LOG = Logger.getLogger(RedactCarCommand.class);
	private static final long serialVersionUID = 8748201015864514297L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		CarsAccessCommand cac = new CarsAccessCommand();
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Ride> rides = manager.getAllRides();
		String rt = request.getParameter("rt");
		LOG.trace("Set the session attribute: command to execute --> " + rt);
		Long carID = Long.parseLong(request.getParameter("carnum"));
		for (Ride x : rides) {
			if (x.getRideStatus()==1&&x.getCarNumber() == carID) {
				throw new AppException("You cannot delete car in ride! Wait for it's return!");
			}
		}
		if ("Delete".equals(rt)) {
			manager.deleteCar(carID);
		} else {
			session.setAttribute("redactcar", manager.getCarByID(carID));
			return Path.PAGE_REDACT_CAR;
		}
		LOG.debug("Command finished");
		return cac.execute(request, response);
	}
}
