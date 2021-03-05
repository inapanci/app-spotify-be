package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
