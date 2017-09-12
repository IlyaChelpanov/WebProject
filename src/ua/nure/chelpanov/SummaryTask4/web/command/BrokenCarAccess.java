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
import ua.nure.chelpanov.SummaryTask4.entity.BrokenCar;
import ua.nure.chelpanov.SummaryTask4.entity.Car;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class BrokenCarAccess extends Command {
	private static final Logger LOG = Logger.getLogger(BrokenCarAccess.class);
	private static final long serialVersionUID = 6145928254746831937L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		List<BrokenCar> bcars = null;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		bcars = manager.getAllBrokenCars();
		List<Car> cars = manager.getAllCars();
		for(BrokenCar bk : bcars){
		for(Car x :cars){
			if(bk.getID()==x.getID()){
				bk.setCar(x);
			}
		}
		}
		
		session.setAttribute("brokencars", bcars);
		
		LOG.debug("Command starts");
		return Path.PAGE_BROKEN_CARS;
	}

}
