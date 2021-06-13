package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import app.spotify.spotifybe.dto.MessageDto;
import app.spotify.spotifybe.exception.BusinessException;
import app.spotify.spotifybe.model.Message;
import app.spotify.spotifybe.model.Ticket;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.MessageRepository;
import app.spotify.spotifybe.repository.TicketRepository;
import app.spotify.spotifybe.repository.TicketStatusRepository;
import app.spotify.spotifybe.repository.TransactionStatusRepository;
import app.spotify.spotifybe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class MessageController {
	
	@Autowired
	MessageRepository messageRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	TicketStatusRepository tStatusRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/message/getById")
	public MessageDto getMessageById(@RequestParam("messageId") long mId) {
		Message m = messageRepo.findById(mId).orElseThrow(() -> new RuntimeException("Cannot find this message."));
		MessageDto dto = new MessageDto();
		dto.setCreatedAt(m.getCreatedAt());
		dto.setDescription(m.getDescription());
		dto.setId(m.getId());
		dto.setRead(m.getRead());
		dto.setTicketId(m.getTicket().getId());
		dto.setUserId(m.getUser().getId());
		return dto;
	}
	
	//user
	@GetMapping("/message/getByTicket")
	public List<MessageDto> getMessageByTicket(@RequestParam("ticketId") int ticketId) {
		List<Message> msgs = messageRepo.findByTicketId(ticketId);
		List<MessageDto> dtos = new ArrayList<>();
		if(!msgs.isEmpty()) {
			for (Message m : msgs) {
				MessageDto dto = new MessageDto();
				dto.setCreatedAt(m.getCreatedAt());
				dto.setDescription(m.getDescription());
				dto.setId(m.getId());
				dto.setRead(m.getRead());
				dto.setTicketId(m.getTicket().getId());
				dto.setUserId(m.getUser().getId());
				dtos.add(dto);
			}
		}
		
		return dtos;
		
	}

	//admin + users
	@PostMapping("/message/addNew")
	public void addNewMessage(@RequestBody MessageDto msg) throws BusinessException {
		Message m = new Message();
		Ticket t = ticketRepo.findById(msg.getTicketId()).orElseThrow(()-> new RuntimeException("Ticket not found."));
		User u = userRepo.findById(msg.getUserId()).orElseThrow(()-> new RuntimeException("User not found."));
		m.setDescription(msg.getDescription());
		m.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		m.setRead(msg.getRead());
		m.setUser(u);
		m.setTicket(t);
		
		if(t.getTicketStatus().getDescription().equals("pending")) {
			Ticket ticket = t;
			ticket.setTicketStatus(tStatusRepo.findByDescription("opened"));
			ticketRepo.save(ticket);
			m.setTicket(ticket);
		}
		//try {
			messageRepo.save(m);
			
//		} catch (Exception e) {
//			throw new BusinessException("Message could not be posted.");
//		}
		//		/return m;
	}
	
//	@PutMapping("message/setIsRead")
//	public void setIsRead(@RequestParam("ticketId") Integer ticketId) {
//		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(()-> new RuntimeException("Wrong ticket chosen."));
//		List<Message> messagesOfTicket = messageRepo.findByTicketId(ticketId);
//		
//		for(Message m : messagesOfTicket) {
//			if(!m.getRead().equals("1")) {
//				m.setRead("1");
//				messageRepo.save(m);
//			}
//		}
//	}
}
