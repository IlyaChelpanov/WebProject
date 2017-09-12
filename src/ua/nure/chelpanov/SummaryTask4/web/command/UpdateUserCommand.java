package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class UpdateUserCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private static final long serialVersionUID = -24009754844365164L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		UserAccessCommand uac = new UserAccessCommand();
		DBManager manager = DBManager.getInstance();
		String redactUsername = request.getParameter("subject");
		LOG.trace("Updating user --> " + redactUsername);
		User user = new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		if (username.length() > 25 || fullName.length() > 25 || password.length() > 25 || email.length() > 35) {
			throw new AppException("Cannot add user with such characteristics");
		}
		user.setUsername(username);
		user.setFullName(fullName);
		user.setPassword(password);
		user.setEmail(email);
		if ("Administrator".equals(request.getParameter("role"))) {
			user.setRoleID(1);
		}
		if ("Dispatcher".equals(request.getParameter("role"))) {
			user.setRoleID(2);
		}
		if ("Driver".equals(request.getParameter("role"))) {
			user.setRoleID(3);
		}
		LOG.trace("New User information --> " + user);
		manager.updateUserByUsername(user, redactUsername);
		LOG.debug("Command finished");
		return uac.execute(request, response);
	}

}
