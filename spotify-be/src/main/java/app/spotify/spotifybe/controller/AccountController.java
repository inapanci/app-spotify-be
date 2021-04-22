package app.spotify.spotifybe.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.spotify.spotifybe.exception.BusinessException;
import app.spotify.spotifybe.importer.FileReader;
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
		return accountRepo.getNumberSold(); 
	}
	
	@GetMapping("/account/findById")
	public Account getById(@RequestParam("accountId") long id) {
		Account a = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account could not be found."));
		return a;
	}
	
//	@PostMapping("/account/addNew")
//	public Account addNewAccount(@RequestBody Account a){
//		Account account = new Account();
//		account.setAddress(a.getAddress());
//		account.setCountry(a.getCountry());
//		account.setCredentials(a.getCredentials());
//		account.setExpire(a.getExpire());
//		account.setExtra(a.getExtra());
//		account.setInvites(a.getInvites());
//		account.setInviteToken(a.getInviteToken());
//		account.setSold(a.getSold());
//		account.setSubscriptionType(a.getSubscriptionType());
//		account.setProduct(a.getProduct());
//		accountRepo.save(account);
//		return account;
//	}

	@PostMapping("/account/upload")
	public List<Account> uploadAccounts(@RequestParam("file") MultipartFile file) throws IOException, BusinessException {
		FileReader fl = new FileReader();
		List<Account> accounts = fl.txtFileToAccount(file.getInputStream());
		try {
			for (Account a : accounts) {
				accountRepo.save(a);
			} 
		} catch (Exception e) {
			throw new BusinessException("Something went wrong. Accounts could not be saved.");
		}
		return accounts;
		
	}
}
