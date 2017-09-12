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
import ua.nure.chelpanov.SummaryTask4.entity.Car;
import ua.nure.chelpanov.SummaryTask4.entity.Order;
import ua.nure.chelpanov.SummaryTask4.entity.User;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class OrderAccessCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private static final long serialVersionUID = -7978029434823256212L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		List<Order> orders = null;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		User user = (User) session.getAttribute("user");
		LOG.trace("Request parameter: user --> " + user);
		List<Car> cars = manager.getAllCars();
		LOG.trace("Found in DB --> " + cars);
		session.setAttribute("cars", cars);
		LOG.trace("UserRole --> " + user.getRoleID());
		if(user.getRoleID() == 3){
		orders = manager.gerUserOrders(user.getID());
		for(int i = 0; i < orders.size(); i++){
			Order x = orders.get(i);
			x.setRace(manager.getRaceByID(x.getRaceID()));
		}
		}
		else{
			orders = manager.getAllOrders();
			for(int i = 0; i < orders.size(); i++){
				Order x = orders.get(i);
				x.setRace(manager.getRaceByID(x.getRaceID()));
			}
		}
		session.setAttribute("orders", orders);
		LOG.debug("Command finished");
		return Path.PAGE_ORDER_PAGE;
	}

}
