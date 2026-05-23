package ecommerce.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.Repository.CustomerRepository;
import ecommerce.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired private CustomerRepository customerRepository;
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


	@Override
	public Customer registerCustomer(Customer cust) {
		Customer c=customerRepository.findByEmail(cust.getEmail());
		if(c==null)
		{String encodedPassword = passwordEncoder.encode(cust.getPwd());
			cust.setPwd(encodedPassword);
			System.out.println(cust);
			
	 return	customerRepository.save(cust);
		}
		else
			return null;
		
		
	}

	@Override
	public List<Customer> allCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return customerRepository.getById(id);
	}

	@Override
	public Customer validate(String userid, String pwd) {
		Customer cc=customerRepository.findByEmail(userid);
		if(cc!=null && passwordEncoder.matches(pwd, cc.getPwd())) {
			return cc;
		}
		return null;
	}
	
	@Override
	public boolean verifyUserId(String userid) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(userid)!=null;
	}

	@Override
	public void updateProfile(Customer cust) {
		// TODO Auto-generated method stub
		if(cust.getPwd().equals("") || cust.getPwd()==null) {
			
			cust.setPwd(findById(cust.getId()).getPwd());
		}
		String encodedPassword = passwordEncoder.encode(cust.getPwd());
		cust.setPwd(encodedPassword);
		
		customerRepository.save(cust);	
	}
	
}
