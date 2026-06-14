package cartlab.services;

import java.util.List;

import cartlab.entities.Customer;
import cartlab.entities.Order;

public interface OrderService {

	Order saveOrder(Order order);
	List<Order> getAllOrders();
	List<Order> getCustomerOrders(Customer customer);
	Order findById(int id);
}
