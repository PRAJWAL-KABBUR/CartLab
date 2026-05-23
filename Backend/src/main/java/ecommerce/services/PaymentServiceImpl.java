package ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.Repository.PaymentRepository;
import ecommerce.entities.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired PaymentRepository paymentRepository;
	
	@Override
	public Payment savePayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findPaymentById(int id) {
		// TODO Auto-generated method stub
		return paymentRepository.getById(id);
	}

}
