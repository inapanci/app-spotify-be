package app.spotify.spotifybe.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.CustomProductPrice;
import app.spotify.spotifybe.repository.CustomProductPriceRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class CustomProductPriceController {
	
	@Autowired
	CustomProductPriceRepository cppRepo;
	
	@GetMapping("/customProductPrice/getAll")
	public List<CustomProductPrice> getAllCustomPrices(){
		return cppRepo.findAll();
	}
	
	@PostMapping("/customProductPrice/postNew")
	public CustomProductPrice postNewCustomPrice(@RequestBody CustomProductPrice customPrice) {
		CustomProductPrice c =  new CustomProductPrice();
		c.setPrice(customPrice.getPrice());
		c.setProduct(customPrice.getProduct());
		c.setStartDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		c.setEndDate(customPrice.getEndDate());
		c.setUser(customPrice.getUser());
		
		cppRepo.save(c);
		
		return c;
	}

}
