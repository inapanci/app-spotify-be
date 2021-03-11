package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.TransactionUserDto;
import app.spotify.spotifybe.model.Transaction;
import app.spotify.spotifybe.repository.TransactionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@GetMapping("/transaction/getAll")
	public List<Transaction> getAllTransactions(){
		return transactionRepo.findAll();
	}
	
	@GetMapping("/transaction/getOne")
	public Object getAllTransactions(@RequestParam long id){
		System.out.println(id);
		return transactionRepo.findById(id);
	}
	
	@GetMapping("/transaction/getTransactionUser")
	public List<TransactionUserDto> getAllTransactionUser(){
		List<Transaction> transactions = transactionRepo.findAll();
		List<TransactionUserDto> transacUser = new ArrayList<>();
		for(Transaction t : transactions) {
			TransactionUserDto dto = new TransactionUserDto();
			dto.setId(t.getId());
			dto.setAmount(t.getAmount());
			dto.setCreatedAt(t.getCreatedAt());
			dto.setDescription(t.getDescription());
			dto.setTransactionStatus(t.getTransactionStatus().getDescription());  //pyet si do vendoset initially
			dto.setTransactionId(t.getTransactionId());
			dto.setPaymentMethod(t.getPaymentMethod().getDescription());
			dto.setUsersUsername(t.getUser().getUsername());
			dto.setUsersEmail(t.getUser().getEmail());
			dto.setUser(t.getUser());
			transacUser.add(dto);
		}
		return transacUser;
	}
	
	@PutMapping("/transaction/updateTransaction")
	public Transaction updateTransactionStatus(@RequestBody Transaction transaction) {
		Transaction t = transactionRepo.findById(transaction.getId()).get();
		t.setTransactionStatus(transaction.getTransactionStatus());
		transactionRepo.save(t);
		return t;
	}
	
	@PostMapping("/transaction/addFunds")
	public Transaction addFunds(@RequestBody Transaction tr) {
		Transaction t = new Transaction();
		t.setAmount(tr.getAmount());
		t.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		t.setDescription(tr.getDescription());
		t.setTransactionId(tr.getTransactionId());
		t.setPaymentMethod(tr.getPaymentMethod());
		t.setTransactionStatus(tr.getTransactionStatus());
		t.setUser(tr.getUser());
		
		transactionRepo.save(t);
		return t;
	}

}
