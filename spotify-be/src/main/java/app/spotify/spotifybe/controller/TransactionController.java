package app.spotify.spotifybe.controller;

import java.math.BigDecimal;
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
import app.spotify.spotifybe.exception.BusinessException;
import app.spotify.spotifybe.model.Transaction;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.PaymentMethodRepository;
import app.spotify.spotifybe.repository.TransactionRepository;
import app.spotify.spotifybe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	PaymentMethodRepository paymentMethodsRepo;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/transaction/getAll")
	public List<Transaction> getAllTransactions() {
		return transactionRepo.findAll();
	}

	@GetMapping("/transaction/getById")
	public Transaction getAllTransactions(@RequestParam long id) {
		return transactionRepo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found."));
	}

	// method gets all transactions but needs only some information for user
	@GetMapping("/transaction/getTransactionUser")
	public List<TransactionUserDto> getAllTransactionUserInfo() {
		List<Transaction> transactions = transactionRepo.findAll();
		List<TransactionUserDto> transacUser = new ArrayList<>();
		for (Transaction t : transactions) {
			TransactionUserDto dto = new TransactionUserDto();
			dto.setId(t.getId());
			dto.setAmount(t.getAmount());
			dto.setCreatedAt(t.getCreatedAt());
			dto.setDescription(t.getDescription());
			dto.setTransactionStatus(t.getTransactionStatus().getDescription()); // pyet si do vendoset initially
			dto.setTransactionId(t.getTransactionId());
			dto.setPaymentMethod(t.getPaymentMethod().getDescription());
			dto.setUsersUsername(t.getUser().getUsername());
			dto.setUsersEmail(t.getUser().getEmail());
			dto.setUser(t.getUser());
			transacUser.add(dto);
		}
		return transacUser;
	}

	@GetMapping("/transaction/usersTransactions")
	public List<TransactionUserDto> getUsersTransactions(@RequestParam("uuid") String uuid) {
		List<Transaction> transactions = transactionRepo.findByUserId(uuid);
		List<TransactionUserDto> transacUser = new ArrayList<>();
		TransactionUserDto dto = new TransactionUserDto();

		if (transactions.isEmpty()) {
			dto.setUsersPaymentMethods(paymentMethodsRepo.getUsersPaymentMethods(uuid));
			transacUser.add(dto);
		} else {
			for (Transaction t : transactions) {
				dto.setId(t.getId());
				dto.setAmount(t.getAmount());
				dto.setCreatedAt(t.getCreatedAt());
				dto.setDescription(t.getDescription());
				dto.setTransactionStatus(t.getTransactionStatus().getDescription()); // pyet si do vendoset initially
				dto.setTransactionId(t.getTransactionId());
				dto.setPaymentMethod(t.getPaymentMethod().getDescription());
				dto.setUser(t.getUser());
				dto.setUsersPaymentMethods(paymentMethodsRepo.getUsersPaymentMethods(uuid));
				transacUser.add(dto);
			}
		}

		return transacUser;
	}

	@PutMapping("/transaction/updateTransaction")
	public Transaction updateTransactionStatus(@RequestBody Transaction transaction) throws BusinessException {
		Transaction t = transactionRepo.findById(transaction.getId())
				.orElseThrow(() -> new RuntimeException("Transaction not found."));
		t.setTransactionStatus(transaction.getTransactionStatus());
		BigDecimal oldAmount = t.getAmount();

		try {
			transactionRepo.save(t);
			User u = null;
			if (oldAmount != transaction.getAmount()) {
				u = userRepo.findById(t.getUser().getId()).get();
				if(u.getBalance()!=null) {
					u.setBalance(u.getBalance().add(transaction.getAmount()));
				}else {
					u.setBalance(transaction.getAmount());
				}		
			}
			userRepo.save(u);
		} catch (Exception e) {
			throw new BusinessException("Transaction could not be saved.");
		}
		return t;
	}

	@PostMapping("/transaction/addFunds")
	public Transaction addFunds(@RequestBody Transaction tr) throws BusinessException {
		Transaction t = new Transaction();
		t.setAmount(tr.getAmount());
		t.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		t.setDescription(tr.getDescription());
		t.setTransactionId(tr.getTransactionId());
		t.setPaymentMethod(tr.getPaymentMethod());
		t.setTransactionStatus(tr.getTransactionStatus());
		t.setUser(tr.getUser());

		try {
			transactionRepo.save(t);
			User u = userRepo.findById(t.getUser().getId()).get();
			if(u.getBalance()!=null) {
				u.setBalance(u.getBalance().add(t.getAmount()));
			}else {
				u.setBalance(t.getAmount());
			}
			userRepo.save(u);
		} catch (Exception e) {
			throw new BusinessException("Transaction could not be saved.");
		}
		return t;
	}

}
