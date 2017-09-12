package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.Race;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class AddRaceCommand extends Command {
	private static final Logger LOG = Logger.getLogger(AddRaceCommand.class);
	private static final long serialVersionUID = -8153993721809199771L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		RacesAccessCommand rac = new RacesAccessCommand();
		DBManager manager = DBManager.getInstance();
		Race race = new Race();
		String dateRegex = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])" + "\\.((19|20)\\d\\d)";
		String timeRegex = "^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$";
		String date =request.getParameter("dateOfRace");
		Pattern pat = Pattern.compile(dateRegex);
		Matcher mat = pat.matcher(date);
		LOG.trace("Request parameter: date --> " + date);
		String startCity = request.getParameter("from");
		LOG.trace("Request parameter: startCity --> " + startCity);
		String endCity = request.getParameter("to");
		LOG.trace("Request parameter: endCity --> " + endCity);
		String time = request.getParameter("timeOfArrive");
		Pattern pat2 = Pattern.compile(timeRegex);
		Matcher mat2 = pat2.matcher(time);
		LOG.trace("Request parameter: time --> " + time);
		if(!mat.find()||startCity.length()>25||endCity.length()>25||!mat2.find()){
			throw new AppException("You cannot add race with such characteristics");
		}
		race.setDate(date);
		race.setStartCity(startCity);
		race.setEndCity(endCity);
		race.setTimeOfArrive(time);
		switch (request.getParameter("typeOfRace")) {
		case ("Cargo, less than 5 tonnes."): {
			race.setTypeOfRace(1);
			break;
		}
		case ("Cargo from 5 to 10 tonnes."): {
			race.setTypeOfRace(2);
			break;
		}
		case ("Cargo more than 10 tonnes."): {
			race.setTypeOfRace(3);
			break;
		}
		case ("Passenger less than 10 people."): {
			race.setTypeOfRace(4);
			break;
		}
		case ("Passenger less than 25 people."): {
			race.setTypeOfRace(5);
			break;
		}
		case ("Passenger less than 50 people."): {
			race.setTypeOfRace(6);
			break;
		}
		}
		LOG.trace("Adding new Race --> " + race);
		manager.addNewRace(race);
		LOG.debug("Command finished");
		return rac.execute(request, response);
	}
}
