package cartlab.services;

import cartlab.entities.Address;

public interface AddressService {
	Address saveAddress(Address address);
	Address findAddress(int id);
}
