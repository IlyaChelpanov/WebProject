package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.mail.util.MailConnectException;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.additional.MailServer;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Ride;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class ExecuteRideCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ExecuteRideCommand.class);
	private static final long serialVersionUID = -4256057945379257505L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		RideAccessCommand rac = new RideAccessCommand();
		String forward = Path.PAGE_ERROR_PAGE;
		DBManager manager = DBManager.getInstance();
		String[] params = request.getParameter("ride").split("-");
		Ride ride = manager.getRideByID(Long.parseLong(params[0]));
		if (ride.getRideStatus() == 2) {
			throw new AppException("You have already checked this ride");
		}
		String rideDate = params[1];
		try {
			if (comparePage(rideDate) >= 0) {
				throw new AppException("Ride date is not today!");
			}
		} catch (ParseException e) {
			throw new AppException("Date format is unacceptable");
		}
		String description = request.getParameter("breakdescrip");

		if (!description.isEmpty()) {
			LOG.trace("Description --> " + description);
			try{
			sendBreakEmail(description, ride);
			}catch(RuntimeException ex){
				LOG.trace("No internet");
				throw new AppException("No internet, please connect!");
			}
			manager.setBrokenCar((long) ride.getCarNumber(), description);
			
		}
		LOG.trace("Ride to execute --> " + ride);
		manager.executeRide(ride.getID());
		forward = rac.execute(request, response);
		LOG.debug("Command finished");
		return forward;
	}

	private void sendBreakEmail(String description, Ride ride) {
		
		String from = "ilyachelpanovv@gmail.com";
		LOG.trace("Sending break message to --> " + from);
		MailServer ms  = new MailServer(from, "leavemealone11e8419d");
		String text = "During the ride " + ride.getID() + " car№" + ride.getCarNumber()+" has been broken"
				+ ",break description is following: " + description + "Driver ID is" + ride.getDriverID();
		String subject = "Car break!";
		ms.send(subject, text, from, from);
		LOG.trace("Mail has been delivered! ");
				
	}

	private int comparePage(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date currentDate = new Date();
		Date rideDate = dateFormat.parse(date);
		return rideDate.compareTo(currentDate);
	}

}
