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
import ua.nure.chelpanov.SummaryTask4.entity.Race;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class RacesAccessCommand extends Command {
	private static final Logger LOG = Logger.getLogger(RacesAccessCommand.class);
	private static final long serialVersionUID = 5919509977592948105L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Race> races = manager.getAllRaces();
		LOG.trace("Found in DB -->" + races);
		session.setAttribute("races", races);
		String forward = Path.PAGE_RACES;
		LOG.debug("Command finished");
		return forward;
	}

}
