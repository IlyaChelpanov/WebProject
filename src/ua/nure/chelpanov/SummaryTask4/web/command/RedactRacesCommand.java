package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class RedactRacesCommand extends Command {

	private static final Logger LOG = Logger.getLogger(RedactRacesCommand.class);
	private static final long serialVersionUID = 5687735103827969635L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		RacesAccessCommand rac = new RacesAccessCommand();
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		String rt = request.getParameter("rt");
		int raceID = Integer.parseInt(request.getParameter("rasesubject"));
		LOG.trace("Request parameter: raceID --> " + raceID);
		if ("Decline".equals(rt)){
			manager.deleteRace(raceID);
		} else{
			session.setAttribute("redactrace", manager.getRaceByID(raceID));
			return Path.PAGE_REDACT_RACE;
		}
		LOG.debug("Command finished");
		return rac.execute(request, response);
	}



}
