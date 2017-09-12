package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Race;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class OrderCommand extends Command {
	private static final Logger LOG = Logger.getLogger(OrderCommand.class);
	private static final long serialVersionUID = 5784181669176577478L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		OrderAccessCommand oac = new OrderAccessCommand();
		String forward = Path.PAGE_ERROR_PAGE;
		DBManager manager = DBManager.getInstance();
		long raceID = Long.parseLong(request.getParameter("orderrace"));
		LOG.trace("Request parameter: raceID --> " + raceID);
		HttpSession session = request.getSession();
		Race race = manager.getRaceByID(raceID);
		LOG.trace("Found in DB --> " + race);
		if (race.getRaceStatusID() == 1) {
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
			if (weight > 1000 || capacity > 150 || speed > 300) {
				throw new AppException("You cannot add car with such characteristics!");
			}
			User user = (User) session.getAttribute("user");
			LOG.info("Settig atribute -->" + user);
			session.setAttribute("Race", race);
			manager.makeAnOrder(weight, capacity, speed, user.getID(), race.getID());
			forward = oac.execute(request, response);
		} else {
			throw new AppException("You cannot choose engaged or denied race!");
		}
		LOG.debug("Command finished");
		return forward;

	}

}
