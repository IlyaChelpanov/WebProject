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

public class UpdateRaceCommand extends Command {
	private static final Logger LOG = Logger.getLogger(UpdateRaceCommand.class);
	private static final long serialVersionUID = -2026211021887852089L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		RacesAccessCommand rac = new RacesAccessCommand();
		DBManager manager = DBManager.getInstance();
		String dateRegex = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])" + "\\.((19|20)\\d\\d)";
		String timeRegex = "^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$";
		Long redactRaceID=  Long.parseLong(request.getParameter("subject"));
		Race newRace = new Race();
		newRace.setId(redactRaceID);
		String date =request.getParameter("date");
		LOG.trace("Request parameter: date --> " + date);
		Pattern pat = Pattern.compile(dateRegex);
		Matcher mat = pat.matcher(date);
		newRace.setDate(date);
		String startCity = request.getParameter("from");
		LOG.trace("Request parameter: startCity --> " + startCity);
		newRace.setStartCity(startCity);
		String endCity = request.getParameter("to");
		LOG.trace("Request parameter: endCity --> " + endCity);
		newRace.setEndCity(endCity);
		String time = request.getParameter("time");
		Pattern pat2 = Pattern.compile(timeRegex);
		Matcher mat2 = pat2.matcher(time);
		LOG.trace("Request parameter: time --> " + time);
		newRace.setTimeOfArrive(time);
		if(!mat.find()||startCity.length()>25||endCity.length()>25||!mat2.find()){
			throw new AppException("You cannot add race with such characteristics");
		}
		switch(request.getParameter("racetype")){
		case("Cargo, less than 5 tonnes"):{
			newRace.setTypeOfRace(1);
			break;
		}
		case("Cargo from 5 to 10 tonnes"):{
			newRace.setTypeOfRace(2);
			break;
		}
		case("Cargo more than 10 tonnes"):{
			newRace.setTypeOfRace(3);
			break;
		}
		case("Passenger less than 10 people"):{
			newRace.setTypeOfRace(4);
			break;
		}
		case("Passenger less than 25 people"):{
			newRace.setTypeOfRace(5);
			break;
		}
		case("Passenger less than 50 people"):{
			newRace.setTypeOfRace(6);
			break;
		}
		}
		
		switch(request.getParameter("racestatus")){
		case("Free"):{
			newRace.setRaceStatusID(1);
			break;
		}
		case("Engaged"):{
			newRace.setRaceStatusID(2);
			break;
		}
		case("Declined"):{
			newRace.setRaceStatusID(3);
			break;
		}
		}
		LOG.trace("Updating race --> " + newRace);
		manager.updateRaceByID(newRace, redactRaceID);
		LOG.debug("Command finished");
		return rac.execute(request, response);		

	}

}
