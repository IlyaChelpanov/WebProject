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
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class UserAccessCommand extends Command {
	private static final Logger LOG = Logger.getLogger(UserAccessCommand.class);
	private static final long serialVersionUID = 646500190799676735L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<User> users = manager.getAllUsers();
		LOG.trace("Found in DB--->" + users);
		session.setAttribute("users", users);
		String forward = Path.PAGE_USERS;
		LOG.debug("Command finished");
		return forward;
	}

}
