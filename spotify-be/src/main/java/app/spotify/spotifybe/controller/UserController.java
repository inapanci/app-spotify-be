package app.spotify.spotifybe.controller;

import app.spotify.spotifybe.dto.UserDashboardDto;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.OrderRepository;
import app.spotify.spotifybe.repository.TicketRepository;
import app.spotify.spotifybe.repository.UserRepository;
import app.spotify.spotifybe.repository.UserStatusRepository;

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
	
	@Autowired
	UserStatusRepository userStatusRepo;
	
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
	
	@PostMapping("/register")
	public User addNewUser(@RequestBody User usr) throws Exception  {
		User u = new User();
		if (userRepo.findByEmail(usr.getEmail()) != null) {
			throw new Exception("user already exists");
		}
		u.setId(usr.getId());
		u.setUsername(usr.getUsername());
		u.setEmail(usr.getEmail());
		u.setPassword(usr.getPassword());
		u.setRole("user");
		u.setBalance(BigDecimal.valueOf(0.0));
		u.setNotifications(usr.getNotifications());
		u.setSignUpDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		u.setUserStatus(userStatusRepo.findById(1).get());
		userRepo.save(u);
		return u; 
	}

}
