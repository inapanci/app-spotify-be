package app.spotify.spotifybe.controller;

import java.math.BigDecimal;
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

import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/user/getAll")
	public List<User> getAllUsers(){
		return userRepo.findAll();
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