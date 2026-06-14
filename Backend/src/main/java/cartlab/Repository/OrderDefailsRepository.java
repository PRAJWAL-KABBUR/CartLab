package cartlab.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartlab.entities.Order;
import cartlab.entities.OrderDetails;

@Repository
public interface OrderDefailsRepository extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByOrder(Order order);
}
