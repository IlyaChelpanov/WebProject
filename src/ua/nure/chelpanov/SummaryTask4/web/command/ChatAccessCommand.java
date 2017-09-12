package ua.nure.chelpanov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.chelpanov.SummaryTask4.Path;
import ua.nure.chelpanov.SummaryTask4.additional.Message;
import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class ChatAccessCommand extends Command {

	private static final long serialVersionUID = 8894757288785993467L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Message> messages = manager.getAllMessages();
		List<User> users = manager.getAllUsers();
		for (Message mes : messages) {
			for (User x : users) {
				if (mes.getUserID() == x.getID()) {
					mes.setUser(x);
				}
			}
		}
		session.setAttribute("messages", messages);

		return Path.CHAT_PAGE;
	}

}
