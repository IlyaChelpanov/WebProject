package ua.nure.chelpanov.SummaryTask4.datebase;

import ua.nure.chelpanov.SummaryTask4.entity.Order;

public enum OrderStatus {
	CONFIRMED, NOT_CONFIRMED, DENIED;

	public static OrderStatus gerOrderStatus(Order order) {
		int orderStatusID = order.getOrderStatusID();
		return OrderStatus.values()[orderStatusID];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
