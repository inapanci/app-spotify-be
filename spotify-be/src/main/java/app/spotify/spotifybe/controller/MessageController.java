package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Message;
import app.spotify.spotifybe.repository.MessageRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class MessageController {
	
	@Autowired
	MessageRepository messageRepo;
	
	@GetMapping("/message/getById")
	public Message getMessageById(@RequestParam("messageId") long mId) {
		return messageRepo.findById(mId).orElseThrow(() -> new RuntimeException("Cannot find this message."));
	}

	@PostMapping("/message/addNew")
	public Message addNewMessage(@RequestBody Message msg) {
		Message m = new Message();
		m.setDescription(msg.getDescription());
		m.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		m.setRead(msg.getRead());
		m.setUser(msg.getUser());
		m.setTicket(msg.getTicket());
		messageRepo.save(m);
		return m;
		
	}
}
