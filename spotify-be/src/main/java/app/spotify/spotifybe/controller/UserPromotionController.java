package app.spotify.spotifybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.UserPromotion;
import app.spotify.spotifybe.repository.UserPromotionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class UserPromotionController {
	
	@Autowired
	UserPromotionRepository userPromoRepo;
	
	//
	@GetMapping("/spotify/getUsersPromo")
	public List<UserPromotion> getAllUsersPromo(@RequestParam("userId") String uuid) {
		return userPromoRepo.findByUserId(uuid);
	}

}
