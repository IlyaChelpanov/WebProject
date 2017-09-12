package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class AddUserCommand extends Command {
	private static final Logger LOG = Logger.getLogger(AddUserCommand.class);
	private static final long serialVersionUID = -3571937169054457745L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		UserAccessCommand uac = new UserAccessCommand();
		DBManager manager = DBManager.getInstance();
		User user = new User();
		String username = request.getParameter("username");
		LOG.trace("Request parameter: username --> " + username);
		String fullName = request.getParameter("fullName");
		LOG.trace("Request parameter: FullName --> " + fullName);
		String password = request.getParameter("password");
		LOG.trace("Request parameter: password --> " + password);
		String email = request.getParameter("email");
		LOG.trace("Request parameter: email --> " + email);
		if(username.length()>25||fullName.length()>25||password.length()>25||email.length()>35){
			throw new AppException("Cannot add user with such characteristicss");
		}
		user.setUsername(username);
		user.setFullName(fullName);
		user.setPassword(password);
		user.setEmail(email);
		System.out.println(request.getParameter("role"));
		LOG.trace("Adding new user --> " + user);
		if ("Administrator".equals(request.getParameter("role"))) {
			user.setRoleID(1);
		}
		if ("Dispatcher".equals(request.getParameter("role"))) {
			user.setRoleID(2);
		}
		if ("Driver".equals(request.getParameter("role"))) {
			user.setRoleID(3);
		}

		manager.addNewUser(user);
		LOG.debug("Command finished");
		return uac.execute(request, response);
		
	}
}
