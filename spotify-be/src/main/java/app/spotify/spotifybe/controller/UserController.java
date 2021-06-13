package app.spotify.spotifybe.controller;

import app.spotify.spotifybe.dto.LoginDto;
import app.spotify.spotifybe.dto.UserDashboardDto;
import app.spotify.spotifybe.dto.UserDto;
import app.spotify.spotifybe.exception.BusinessException;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.OrderRepository;
import app.spotify.spotifybe.repository.TicketRepository;
import app.spotify.spotifybe.repository.UserRepository;
import app.spotify.spotifybe.repository.UserStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

	//admin
	@GetMapping("/user/getAll")
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> dtoList = new ArrayList<>();
		for (User u : users) {
			UserDto dto = new UserDto();
			dto.setId(u.getId());
			dto.setBalance(u.getBalance());
			dto.setEmail(u.getEmail());
			dto.setLastSignIn(u.getLastSignIn());
			dto.setNotifications(u.getNotifications());
			dto.setPassword(u.getPassword());
			dto.setRole(u.getRole());
			dto.setSignUpDate(u.getSignUpDate());
			dto.setUsername(u.getUsername());
			dto.setUserStatus(u.getUserStatus().getDescription());
			dtoList.add(dto);
		}
		return dtoList;
	}

	//admin + user
	@GetMapping("/user/getById")
	public User getById(@RequestParam("userId") String uuid) {
		return userRepo.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));
	}

	//admin + user
	@GetMapping("/user/userDetails")
	public UserDashboardDto getUserDetails(@RequestParam("userId") String uuid) {
		UserDashboardDto dto = new UserDashboardDto();
		User user = userRepo.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));
		if (orderRepo.getAccountsPurchased(uuid) != null) {
			dto.setAccountsPurchased(orderRepo.getAccountsPurchased(uuid));
		}
		if (orderRepo.getAmountSpent(uuid) != null) {
			dto.setAmountSpent(orderRepo.getAmountSpent(uuid));
		}
		dto.setLastSignIn(user.getLastSignIn());
		dto.setSignUpDate(user.getSignUpDate());
		dto.setTotalOrders(orderRepo.findByUserId(uuid).size());
		dto.setTotalTickets(ticketRepo.findByUserId(uuid).size());
		if (userRepo.getOnlineStaff() != null) {
			dto.setStaffOnline(userRepo.getOnlineStaff());
		}

		return dto;
	}

	//admin + user
	@PutMapping("/user/update")
	public User updateUser(@RequestBody User user) throws BusinessException {
		User u = userRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("user not found"));
		if(user.getEmail()!=null) {
			u.setEmail(user.getEmail());
		}
		if(user.getUsername()!=null) {
			u.setUsername(user.getUsername());
		}
		if(user.getPassword()!=null) {
			u.setPassword(user.getPassword());
		}
		if(user.getBalance()!=null) {
			u.setBalance(user.getBalance());
		}
		if(user.getUserStatus()!=null) {
			u.setUserStatus(user.getUserStatus());
		}
		if(user.getPaymentMethods()!=null) {
			u.setPaymentMethods(user.getPaymentMethods());
		}
		try {
			userRepo.save(u);
		} catch (Exception e) {
			throw new BusinessException("User settings could not be updated.");
		}
		return user;

	}

	@PostMapping("/register")
	public HttpStatus addNewUser(@RequestBody User usr) throws Exception {
		User u = new User();
		if (userRepo.findByEmail(usr.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");

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
		return HttpStatus.OK;
	}

	@PostMapping("/login")
	public LoginDto login(@RequestParam(required = true) String email, @RequestParam(required = true) String password) {

		User user = userRepo.findByEmailAndPassword(email, password);
		LoginDto loginData = new LoginDto();

		if (user != null) {

			loginData.setEmail(user.getEmail());
			loginData.setRole(user.getRole());
			loginData.setUsername(user.getUsername());
			loginData.setToken(user.getId());
			
			user.setLastSignIn(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			user.setOnline("1");
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong credentials");
		}

		return loginData;
	}

}
