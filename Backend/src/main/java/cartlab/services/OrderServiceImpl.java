package cartlab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartlab.Repository.OrderRepository;
import cartlab.entities.Customer;
import cartlab.entities.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired OrderRepository orderRepository;
	
	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return  orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return  orderRepository.findAll();
	}

	@Override
	public List<Order> getCustomerOrders(Customer customer) {
		// TODO Auto-generated method stub
		return  orderRepository.findByCustomer(customer);
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return  orderRepository.getById(id);
	}

	
}
