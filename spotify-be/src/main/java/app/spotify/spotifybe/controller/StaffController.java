package app.spotify.spotifybe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Staff;
import app.spotify.spotifybe.repository.StaffRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class StaffController {
	
	@Autowired
	StaffRepository staffRepo;
	
	@GetMapping("staff/getOnline")
	public List<Staff> getOnlineStaff(){
		return staffRepo.findOnlineStaff();
	}

}
