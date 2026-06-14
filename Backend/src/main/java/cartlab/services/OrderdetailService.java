package cartlab.services;

import java.util.List;

import cartlab.entities.Order;
import cartlab.entities.OrderDetails;

public interface OrderdetailService {

	void saveOrderDetails(OrderDetails od);
	OrderDetails findById(int id);
	List<OrderDetails> findByOrder(Order order);
}
