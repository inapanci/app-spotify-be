package app.spotify.spotifybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Announcement;
import app.spotify.spotifybe.repository.AnnouncementRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class AnnouncementController {
	
	@Autowired
	AnnouncementRepository announcementRepo;
	
	@GetMapping("/announcement/getAll")
	public List<Announcement> getAll(){
		return announcementRepo.findAll();
	}

}
