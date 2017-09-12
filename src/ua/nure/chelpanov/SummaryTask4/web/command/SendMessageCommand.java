package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.chelpanov.SummaryTask4.additional.Message;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class SendMessageCommand extends Command {

	private static final long serialVersionUID = 477921664493539882L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		ChatAccessCommand cac = new ChatAccessCommand();
		String context = request.getParameter("message");
		User user = (User) session.getAttribute("user");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
		Date date = new Date();
		String sdate = dateFormat.format(date);
		manager.addNewMessage(context, user, sdate);
		List<Message> messages = manager.getAllMessages();
		if (messages.size() > 20) {
			manager.deleteMessage(messages.get(0));
			messages.remove(0);
		}
		return cac.execute(request, response);
	}

}
