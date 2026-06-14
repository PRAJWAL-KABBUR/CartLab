package cartlab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cartlab.entities.Customer;
import cartlab.models.LoginDTO;
import cartlab.models.Response;
import cartlab.services.CustomerService;
import cartlab.services.Email;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@Autowired
	private Email email;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Customer cust) {		
	Customer c=	customerService.registerCustomer(cust);
	if(c==null)
		return  Response.status(HttpStatus.NOT_FOUND);
	else
		email.simpleEmail(cust.getEmail(), "Welcome to E-Store of farming", "Registeration Complete");
		return Response.success(cust);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllCustomers() {
		List<Customer> result = customerService.allCustomers();
		return Response.success(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {
		Customer result = customerService.findById(id);
		System.out.println(result);
		return Response.success(result);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Customer user=customerService.validate(dto.getEmail(),dto.getPwd());
		if(user!=null)
			return Response.success(user);
		else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProfile(@RequestBody Customer cust,@PathVariable("id") int id) {
		customerService.updateProfile(cust);
		return Response.status(HttpStatus.OK);
	}

}
