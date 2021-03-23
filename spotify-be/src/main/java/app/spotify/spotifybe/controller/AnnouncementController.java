package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/announcement/addNew")
	public Announcement addNew(@RequestBody Announcement a) {
		Announcement an = new Announcement();
		an.setDescription(a.getDescription());
		an.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		an.setUser(a.getUser());
		announcementRepo.save(an);
		return an;
	}
	
	@DeleteMapping("/announcement/deleteAnnouncement")
	public void deleteAnnouncement(@RequestBody Announcement a) {
		Announcement an = announcementRepo.findById(a.getId()).orElseThrow(()-> new RuntimeException("announcement not found."));
		announcementRepo.delete(an);
	}

}
