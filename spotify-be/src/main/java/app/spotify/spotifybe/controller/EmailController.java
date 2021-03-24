package app.spotify.spotifybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Email;
import app.spotify.spotifybe.repository.EmailRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class EmailController {
	
	@Autowired
	EmailRepository emailRepo;
	
	@GetMapping("/email/getLatest")
	public Email getLatestEmail() {
		return emailRepo.findIdMax();
	}

}
