package app.spotify.spotifybe.controller;

import java.io.IOException;
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
import app.spotify.spotifybe.exception.BusinessException;
import app.spotify.spotifybe.model.Account;
import app.spotify.spotifybe.model.Filter;
import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.repository.AccountRepository;
import app.spotify.spotifybe.repository.FilterRepository;
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

	@Autowired
	FilterRepository filterRepo;

	//admin + user
	@GetMapping("/product/getAll")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	//admin
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

	//admin
	@GetMapping("/product/getById")
	public Product getProductById(@RequestParam("prodId") int prodId) { 
		return productRepo.findById(prodId).orElseThrow(() -> new RuntimeException("product not found."));
	}

	//user
	@GetMapping("/product/getAccountProductInfo")
	public AccountProductDto getAccountProductInfo(@RequestParam("prodId") int prodId,
			@RequestBody(required = false) List<Filter> filters) throws BusinessException {
		
		AccountProductDto dto = new AccountProductDto();
		List<String> subTypes = accountRepo.findDistinctSubscriptions();
		Product p = productRepo.findById(prodId).orElseThrow(() -> new RuntimeException("product not found."));

		List<String> finalReturn = new ArrayList<>();
		for (String s : subTypes) {
			String finalS = s;
			if (s.charAt(0) == ' ' || s.charAt(s.length() - 1) == ' ') {
				finalS = s.trim();
			}
			if (finalReturn.contains(finalS))
				continue;
			finalReturn.add(s);
		}
		dto.setSubscriptionTypes(finalReturn);
		dto.setCountries(accountRepo.findDistinctCountries());
		dto.setFormats(productRepo.findAllFormat());
		dto.setProduct(p);

		int stock = accountController.isThereStock(filters, p.getId());
		dto.setStock(stock);
		return dto;
	}

	//admin
	@PostMapping(value = "/product/addProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Product addNewProduct(@RequestPart("productInfo") String productInfo,
			@RequestPart("productImage") MultipartFile file, @RequestPart("accounts") MultipartFile accountsFile)
			throws IOException, BusinessException {
		Product product = new ObjectMapper().readValue(productInfo, Product.class);
		product.setProductImage(file.getBytes());
		try {
			productRepo.save(product);
		} catch (Exception e) {
			throw new BusinessException("Something went wrong. Product could not be saved.");
		}
		List<Account> accounts = accountController.uploadAccounts(accountsFile);
		try {
			for (Account a : accounts) {
				a.setProduct(product);
				accountRepo.save(a);
			} 
		} catch (Exception e) {
			throw new BusinessException("Something went wrong. Product accounts could not be uploaded.");		
		}
		return product;
	}

	//admin
	@PutMapping(value = "/product/updateProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Product updateProduct(@RequestPart(name = "productInfo", required = false) String productInfo,
			@RequestPart(name = "productImage", required = false) MultipartFile file,
			@RequestPart(name = "accounts", required = false) MultipartFile accountsFile) throws IOException, BusinessException {

		Product p = new ObjectMapper().readValue(productInfo, Product.class);
		Product prod = productRepo.findById(p.getId()).orElseThrow(() -> new RuntimeException("product not found"));

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
		try {
			productRepo.save(prod);
		} catch (Exception e) {
			throw new BusinessException("Something went wrong. Product could not be saved.");
		}

		try {
			if (accountsFile != null) {
				List<Account> accounts = accountController.uploadAccounts(accountsFile);
				for (Account a : accounts) {
					a.setProduct(prod);
					accountRepo.save(a);
				}
			} 
		} catch (Exception e) {
			throw new BusinessException("Something went wrong. Product accounts could not be uploaded.");	
		}
		return prod;

	}

	// not to create new controller
	@GetMapping("/getfilters")
	public List<Filter> getAllFilters() {
		return filterRepo.findAll();
	}

}
