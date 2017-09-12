package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.datebase.Role;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class LoginCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private static final long serialVersionUID = 1892175717185896550L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		DBManager manager = DBManager.getInstance();
		LOG.trace("Request parameter: loging --> " + username);

		String password = request.getParameter("password");
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			
			throw new AppException("Login/password cannot be empty");
		}

		User user = manager.getUserByUsername(username);
		LOG.trace("Found in DB: user --> " + user);
		if (user == null || !password.equals(user.getPassword())) {
			
			throw new AppException("Cannot find user with such login/password");
		}
		Role userRole = Role.getRole(user);
		String forward = Path.PAGE_ERROR_PAGE;
		if (userRole != null) {
			forward = Path.PAGE_PERSONAL_CABINET;
		}
		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.info("User " + user + " logged as " +
		userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return forward;

	}

}
