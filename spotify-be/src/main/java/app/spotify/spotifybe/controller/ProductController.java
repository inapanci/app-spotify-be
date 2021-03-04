package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.ProductAccountsDto;
import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.repository.AccountRepository;
import app.spotify.spotifybe.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class ProductController {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@GetMapping("/product/getAll")
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	@GetMapping("/product/allProductsAndAccounts")
	public List<ProductAccountsDto> getProductsAndNrAccounts(){
		List<Product> products = productRepo.findAll();
		List<ProductAccountsDto> prodAccounts = new ArrayList<>();
		int nrOfAcc = 0;
		for(Product p: products) {
			ProductAccountsDto dto = new ProductAccountsDto();
			dto.setProduct(p);
			nrOfAcc = accountRepo.findByProductId(p.getId());
			dto.setNrOfAccounts(nrOfAcc);
			prodAccounts.add(dto);
		}
		return prodAccounts;
	}
	
	@PostMapping("/product/addProduct")
	public Product addNewProduct(@RequestBody Product p) {
		Product product = new Product();
		product.setTitle(p.getTitle());
		product.setMaximum(p.getMaximum());
		product.setMinimum(p.getMinimum());
		product.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		product.setCustomProductPrices(p.getCustomProductPrices());
		product.setGate(p.getGate());
		product.setDescription(p.getDescription());
		product.setFormat(p.getFormat());
		product.setPrice(p.getPrice());
		product.setProductStatus(p.getProductStatus());
		product.setSort(p.getSort());
		product.setDeliveryTime(p.getDeliveryTime());
		product.setWarranty(p.getWarranty());
		productRepo.save(product);
		return product;
	}
	
	
}
