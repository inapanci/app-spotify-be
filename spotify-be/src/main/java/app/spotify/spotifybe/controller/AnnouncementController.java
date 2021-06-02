package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.AnnouncementDto;
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
		List<Announcement> list = announcementRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
		if(!list.isEmpty()) {
			return list;
		}else
			return new ArrayList<>();
		
	}
	
	@PostMapping("/announcement/addNew")
	public AnnouncementDto addNew(@RequestBody Announcement a) {
		Announcement an = new Announcement();
		an.setDescription(a.getDescription());
		an.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		an.setUser(a.getUser());
		announcementRepo.save(an);
		AnnouncementDto dto = new AnnouncementDto();
		dto.setCreatedAt(an.getCreatedAt());
		dto.setUserId(an.getUser().getId());
		dto.setDescription(an.getDescription());
		dto.setId(an.getId());
		return dto;
	}
	
	@DeleteMapping("/announcement/deleteAnnouncement")
	public void deleteAnnouncement(@RequestBody Announcement a) {
		Announcement an = announcementRepo.findById(a.getId()).orElseThrow(()-> new RuntimeException("announcement not found."));
		announcementRepo.delete(an);
	}

}
