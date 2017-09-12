package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.entity.Race;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class SortRaceCommand extends Command {
	private static final Logger LOG = Logger.getLogger(SortRaceCommand.class);
	private static final long serialVersionUID = 3747356680318755776L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		IDComparator idcomp = new IDComparator();
		DateComparator datecomp = new DateComparator();
		StatusComparator statuscomp = new StatusComparator();
		HttpSession session = request.getSession();
		List<Race> races = (List<Race>) session.getAttribute("races");
		String command = request.getParameter("sortType");
		LOG.trace("Sort command --->" + command);

		if ("ID".equals(command)) {
			races.sort(idcomp);
		}
		if ("Date".equals(command)) {
			races.sort(datecomp);
		}
		if ("Status".equals(command)) {
			races.sort(statuscomp);
		}
		session.setAttribute("races", races);
		LOG.debug("Command finished");
		return Path.PAGE_RACES;
	}

	private class IDComparator implements Comparator<Race> {

		@Override
		public int compare(Race o1, Race o2) {
			return o1.getID().compareTo(o2.getID());
		}

	}

	private class DateComparator implements Comparator<Race> {

		@Override
		public int compare(Race o1, Race o2) {

			Date date = null;
			Date date2 = null;
			try {
				date = new SimpleDateFormat("dd.MM.yyyy").parse(o1.getDate());
				date2 = new SimpleDateFormat("dd.MM.yyyy").parse(o2.getDate());
			} catch (ParseException e) {

				e.printStackTrace();
			}

			return date.compareTo(date2);
		}

	}

	private class StatusComparator implements Comparator<Race> {

		@Override
		public int compare(Race o1, Race o2) {

			return o1.getRaceStatusID() - o2.getRaceStatusID();
		}

	}
}
