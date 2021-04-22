package app.spotify.spotifybe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.exception.NotManagedException;
import app.spotify.spotifybe.model.PaymentMethod;
import app.spotify.spotifybe.repository.PaymentMethodRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class PaymentMethodController {
	
	@Autowired
	PaymentMethodRepository paymentMethRepo;
	
	@GetMapping("/paymentMethod/getAll")
	public List<PaymentMethod> getAllPaymentMethods(){
		return paymentMethRepo.findAll();
	}
	
	@GetMapping("/paymentMethod/getById")
	public PaymentMethod getById(@RequestParam("pmId") int pmId) {
		return paymentMethRepo.findById(pmId).orElseThrow(()-> new RuntimeException("Payment method could not be found"));
	}
	
	@PutMapping("/paymentMethod/updateSettings")
	public List<PaymentMethod> updatePmSettings(@RequestBody PaymentMethod[] pm) throws NotManagedException {
		List<PaymentMethod> list = new ArrayList<>(); //= paymentMethRepo.findById(pm.getId()).orElseThrow(()-> new RuntimeException("payment method not found"));

		try {
			for(PaymentMethod p : pm) {
				paymentMethRepo.save(p);
				list.add(p);
			}
		} catch (Exception e) {
			throw new NotManagedException("Something went wrong. Settings were not saved.");
		}
		
		return list;
	}
}
