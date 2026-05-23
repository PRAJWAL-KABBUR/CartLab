package ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.Repository.ProductRepository;
import ecommerce.Repository.SellerRepository;
import ecommerce.entities.Product;
import ecommerce.entities.Seller;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired 
	private SellerRepository sellerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public Seller registerSeller(Seller seller) {
		// TODO Auto-generated method stub
		String encodedPassword = passwordEncoder.encode(seller.getPwd());
		seller.setPwd(encodedPassword);
		 return sellerRepository.save(seller);
	}

	@Override
	public List<Seller> allSellers() {
		// TODO Auto-generated method stub
		return sellerRepository.findAll();
	}

	@Override
	public Seller findById(int id) {
		// TODO Auto-generated method stub
		return sellerRepository.getById(id);
	}

	@Override
	public Seller validate(String email, String pwd) {
		Seller seller=sellerRepository.findByEmail(email);
		if(seller!=null && passwordEncoder.matches(pwd, seller.getPwd())) {
			return seller;
		}
		return null;
	}

	@Override
	public void deleteSeller(int id) {
		// TODO Auto-generated method stub
		Seller seller=sellerRepository.getById(id);
		List<Product> productList = productRepository.findBySeller(seller, null);
		productRepository.deleteAll(productList);
		sellerRepository.delete(seller);
	}

}
