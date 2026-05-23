package ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.Repository.AddressRepository;
import ecommerce.entities.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired AddressRepository addressRepository;
	
	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public Address findAddress(int id) {
		// TODO Auto-generated method stub
		return addressRepository.getById(id);
	}

}
