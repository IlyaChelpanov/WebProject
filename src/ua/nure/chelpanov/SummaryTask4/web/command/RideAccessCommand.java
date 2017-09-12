package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Ride;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class RideAccessCommand extends Command {
	private static final Logger LOG = Logger.getLogger(RideAccessCommand.class);
	private static final long serialVersionUID = -2334932397914596641L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		User user = (User) session.getAttribute("user");
		List<Ride> rides = manager.getAllRides();
		LOG.trace("Found in DB--> " + rides);
		for(Ride x: rides){
			x.setRace(manager.getRaceByID(x.getRaceNumberID()));
			x.setCar(manager.getCarByID((long)x.getCarNumber()));
			x.setUser(manager.getUserByID(x.getDriverID()));
		}
		if(user.getRoleID()==3){
			Iterator<Ride> iterator = rides.iterator();
			while(iterator.hasNext()){
				if(iterator.next().getDriverID()!=user.getID()){
					iterator.remove();
				}
			}
		}
		
		session.setAttribute("rides", rides);
		LOG.debug("Command finished");
		return Path.PAGE_RIDE;
	}

}
