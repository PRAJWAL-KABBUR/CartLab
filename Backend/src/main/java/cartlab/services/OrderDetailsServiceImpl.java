package cartlab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartlab.Repository.OrderDefailsRepository;
import cartlab.entities.Order;
import cartlab.entities.OrderDetails;

@Service
public class OrderDetailsServiceImpl implements OrderdetailService {

	@Autowired OrderDefailsRepository orderDefailsRepository;
	@Override
	public void saveOrderDetails(OrderDetails od) {
		// TODO Auto-generated method stub
		orderDefailsRepository.save(od);
	}

	@Override
	public OrderDetails findById(int id) {
		// TODO Auto-generated method stub
		return orderDefailsRepository.getById(id);
	}

	@Override
	public List<OrderDetails> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDefailsRepository.findByOrder(order);
	}

}
