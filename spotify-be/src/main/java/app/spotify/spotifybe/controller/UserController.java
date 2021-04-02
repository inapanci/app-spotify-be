package app.spotify.spotifybe.controller;

import app.spotify.spotifybe.dto.UserDashboardDto;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.OrderRepository;
import app.spotify.spotifybe.repository.TicketRepository;
import app.spotify.spotifybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@GetMapping("/user/getAll")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/user/getById")
	public User getById(@RequestParam("userId") String uuid) {
		return userRepo.findById(uuid).orElseThrow(()->new RuntimeException("user not found"));
	}

	
	@GetMapping("/user/userDetails")
	public UserDashboardDto getUserDetails(@RequestParam("userId") String uuid) {
		UserDashboardDto dto = new UserDashboardDto();
		User user = userRepo.findById(uuid).orElseThrow(()-> new RuntimeException("user not found"));
		dto.setAccountsPurchased(orderRepo.getAccountsPurchased(uuid));
		dto.setAmountSpent(orderRepo.getAmountSpent(uuid));
		dto.setLastSignIn(user.getLastSignIn());
		dto.setSignUpDate(user.getSignUpDate());
		dto.setTotalOrders(orderRepo.findByUserId(uuid).size());
		dto.setTotalTickets(ticketRepo.findByUserId(uuid).size());	
		dto.setStaffOnline(userRepo.getOnlineStaff());
		return dto;
	}
	
	@PutMapping("/user/update")
	public User updateUser(@RequestBody User user) {
		User u = userRepo.findById(user.getId()).orElseThrow(()-> new RuntimeException("user not found"));
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setBalance(user.getBalance());
		u.setUserStatus(user.getUserStatus());
		u.setPaymentMethods(user.getPaymentMethods());
		userRepo.save(u);
		return user;
		
	}
	
	@PostMapping("/user/addNew")
	public User addNewUser(@RequestBody User usr) {
		User u = new User();
		u.setId(usr.getId());
		u.setUsername(usr.getUsername());
		u.setEmail(usr.getEmail());
		u.setPassword(usr.getPassword());
		u.setRole(usr.getRole());
		u.setBalance(BigDecimal.valueOf(0.0));
		u.setLastSignIn(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		u.setNotifications(usr.getNotifications());
		u.setSignUpDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		u.setUserStatus(usr.getUserStatus());
		userRepo.save(u);
		return u; 
	}

}
