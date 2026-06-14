package cartlab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartlab.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
