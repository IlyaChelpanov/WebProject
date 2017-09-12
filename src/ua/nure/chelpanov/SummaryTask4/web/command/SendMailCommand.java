package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.additional.MailServer;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class SendMailCommand extends Command {

	private static final long serialVersionUID = 9060372618996323101L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		String from = request.getParameter("from");
		MailServer ms = new MailServer(from, "leavemealone11e8419d");
		String to = request.getParameter("to");
		String subject = request.getParameter("subj");
		String content = request.getParameter("body");
		if((from == null) || (to == null)
		|| (subject == null) || (content == null)) {
			throw new AppException("Fields cannot be empty");
	}
		ms.send(subject, content, from, to);
		return Path.PAGE_LOGIN;
	}

}
