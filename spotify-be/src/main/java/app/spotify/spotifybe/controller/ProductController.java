package app.spotify.spotifybe.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.spotify.spotifybe.dto.AccountProductDto;
import app.spotify.spotifybe.dto.ProductAccountsDto;
import app.spotify.spotifybe.model.Account;
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

	@Autowired
	AccountController accountController;

	@GetMapping("/product/getAll")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@GetMapping("/product/allProductsAndAccounts")
	public List<ProductAccountsDto> getProductsAndNrAccounts() {
		List<Product> products = productRepo.findAll();
		List<ProductAccountsDto> prodAccounts = new ArrayList<>();
		int nrOfAcc = 0;
		for (Product p : products) {
			ProductAccountsDto dto = new ProductAccountsDto();
			dto.setId(p.getId());
			dto.setCreatedAt(p.getCreatedAt());
			dto.setDeliveryTime(p.getDeliveryTime());
			dto.setDescription(p.getDescription());
			dto.setFormat(p.getFormat());
			dto.setGate(p.getGate());
			dto.setMaximum(p.getMaximum());
			dto.setMinimum(p.getMinimum());
			dto.setPrice(p.getPrice());
			dto.setProductImage(p.getProductImage());
			dto.setSort(p.getSort());
			dto.setTitle(p.getTitle());
			dto.setWarranty(p.getWarranty());
			nrOfAcc = accountRepo.findByProductId(p.getId()).size();
			dto.setNrOfAccounts(nrOfAcc);
			prodAccounts.add(dto);
		}
		return prodAccounts;
	}
	
	@GetMapping("/order/getAccountProductInfo")
	public AccountProductDto getAccountProductInfo(@RequestBody Product product) {
		AccountProductDto dto = new AccountProductDto();
		List<String> subTypes = accountRepo.findDistinctSubscriptions();

		List<String> finalReturn = new ArrayList<>();
		for(String s: subTypes) {
			String finalS = s;
			if(s.charAt(0) == ' ' || s.charAt(s.length() -1) == ' ') {
				finalS = s.trim();
			}
			if(finalReturn.contains(finalS)) continue;
			finalReturn.add(s);
		}
		dto.setSubscriptionTypes(finalReturn);
		dto.setCountries(accountRepo.findDistinctCountries());
		dto.setFormats(productRepo.findAllFormat());
		dto.setProduct(product);
		dto.setStock(accountRepo.findByProductId(product.getId()).size());
		return dto;
	}

	@PostMapping(value = "/product/addProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Product addNewProduct(@RequestPart("productInfo") String productInfo,
			@RequestPart("productImage") MultipartFile file, @RequestPart("accounts") MultipartFile accountsFile)
			throws IOException {
		Product product = new ObjectMapper().readValue(productInfo, Product.class);
		product.setProductImage(file.getBytes());
		productRepo.save(product);

		List<Account> accounts = accountController.uploadAccounts(accountsFile);
		for (Account a : accounts) {
			a.setProduct(product);
			accountRepo.save(a);
		}

		return product;
	}

	@PutMapping(value = "/product/updateProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Product updateProduct(@RequestPart(name = "productInfo", required = false) String productInfo,
			@RequestPart(name = "productImage", required = false) MultipartFile file,
			@RequestPart(name = "accounts", required = false) MultipartFile accountsFile) throws IOException {

		Product p = new ObjectMapper().readValue(productInfo, Product.class);
		Product prod = productRepo.findById(p.getId())
				.orElseThrow(() -> new RuntimeException("product not found"));

		prod.setCreatedAt(p.getCreatedAt());
		prod.setDeliveryTime(p.getDeliveryTime());
		prod.setFormat(p.getFormat());
		prod.setDescription(p.getDescription());
		prod.setGate(p.getGate());
		prod.setMaximum(p.getMaximum());
		prod.setMinimum(p.getMinimum());
		prod.setPrice(p.getPrice());
		prod.setProductStatus(p.getProductStatus());
		prod.setSort(p.getSort());
		prod.setTitle(p.getTitle());
		prod.setWarranty(p.getWarranty());
		
		if (file != null) {
			prod.setProductImage(file.getBytes());
		}
		productRepo.save(prod);

		if (accountsFile != null) {
			List<Account> accounts = accountController.uploadAccounts(accountsFile);
			for (Account a : accounts) {
				a.setProduct(prod);
				accountRepo.save(a);
			}
		}

		return prod;

	}

}
