package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;


public class LogoutCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
	private static final long serialVersionUID = -5517894759320539458L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}

