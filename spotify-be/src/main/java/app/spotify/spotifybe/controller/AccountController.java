package app.spotify.spotifybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Account;
import app.spotify.spotifybe.repository.AccountRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepo;
	
	@GetMapping("/account/getAllSold")
	public int getAllSoldAccount(){
		int nrSold = accountRepo.getNumberSold(); 
		return nrSold;
	}
	
	@GetMapping("/hello")
	public String sayHi() {
		return "workssss";
	}

}
