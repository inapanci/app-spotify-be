package app.spotify.spotifybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
