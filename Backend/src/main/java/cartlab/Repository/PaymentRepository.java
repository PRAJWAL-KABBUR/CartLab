package cartlab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cartlab.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
