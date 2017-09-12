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
import ua.nure.chelpanov.SummaryTask4.entity.Order;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;

public class OrderAcceptCommand extends Command {
	private static final Logger LOG = Logger.getLogger(OrderAcceptCommand.class);
	private static final long serialVersionUID = -136424114436718321L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		OrderAccessCommand oac = new OrderAccessCommand();
		DBManager manager = DBManager.getInstance();
		Long orderID = Long.parseLong(request.getParameter("order"));
		LOG.trace("Request parameter: orderID --> " + orderID);
		String carInfo = request.getParameter("car");
		LOG.trace("Request parameter: carInfo --> " + carInfo);
		String[] info = carInfo.split("-");
		Order order = manager.getOrderByID(orderID);
		LOG.trace("Found in DB --> " + order);
		String forward = Path.PAGE_ERROR_PAGE;
		if (order.getOrderStatusID() == 2) {
			List<Order> allOrders = manager.getAllOrders();
			for (int i = 0; i < allOrders.size(); i++) {
				if (order.getRaceID() == allOrders.get(i).getRaceID()) {
					manager.denyOrder(allOrders.get(i).getID());
				}

			}
			manager.acceptOrder(order.getID(), info[0], order.getDriverID(), order.getRaceID());
			forward = oac.execute(request, response);
		} else {
			throw new AppException("Order has already been accepted/denied");
		}
		LOG.debug("Command finished");
		return forward;
	}

}
