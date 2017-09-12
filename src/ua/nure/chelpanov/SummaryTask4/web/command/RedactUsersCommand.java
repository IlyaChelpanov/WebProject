package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;
import ua.nure.chelpanov.SummaryTask4.exceptions.Messages;

public class RedactUsersCommand extends Command {
	private static final Logger LOG = Logger.getLogger(RedactUsersCommand.class);
	private static final long serialVersionUID = 3148716816112658236L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		UserAccessCommand uac = new UserAccessCommand();
		DBManager manager = DBManager.getInstance();
		String rt = request.getParameter("rt");
		String userlog = request.getParameter("user");
		if ("Delete".equals(rt)) {
			delete(userlog, manager);
		} else {
			session.setAttribute("redactuser", manager.getUserByUsername(userlog));
			return Path.PAGE_REDACT_USER;
		}
		return uac.execute(request, response);
	}

	private void delete(String userlog, DBManager manager) throws AppException {
		User user = manager.getUserByUsername(userlog);
		LOG.trace("Found in DB --> " + user);
		if (user.getRoleID() == 1) {
			throw new AppException(Messages.ERR_CANNOT_DELETE_ADMINISTRATOR);
		}
		LOG.debug("Command finished");
		manager.deleteUser(userlog);

	}
}
