package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.TicketUserDto;
import app.spotify.spotifybe.model.Ticket;
import app.spotify.spotifybe.repository.TicketRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class TicketController {

	@Autowired
	TicketRepository ticketRepo;

	@GetMapping("/ticket/getAll")
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

	@GetMapping("/ticket/getById")
	public Ticket getTicketById(@RequestParam("ticketId") int ticketId) {
		return ticketRepo.findById(ticketId).orElseThrow(() -> new RuntimeException("ticket not found."));
	}

	@GetMapping("/ticket/getAllOfUser")
	public List<Ticket> getTicketsOfUser(@RequestParam("userId") String uuid) {
		return ticketRepo.findByUserId(uuid);
	}
	
	@GetMapping("/ticket/getAllOfUserDto")
	public List<TicketUserDto> getTicketUser(@RequestParam("userId") String uuid){
		List<Ticket> tickets = ticketRepo.findByUserId(uuid);
		List<TicketUserDto> ticketUser = new ArrayList<>();
		for(Ticket t : tickets) {
			TicketUserDto dto = new TicketUserDto();
			dto.setId(t.getId());
			dto.setCreatedAt(t.getCreatedAt());
			dto.setNotes(t.getNotes());
			dto.setStatus(t.getTicketStatus().getDescription());
			dto.setTitle(t.getTitle());
			dto.setUpdatedAt(t.getUpdatedAt());
			dto.setUser(t.getUser());
			ticketUser.add(dto);
		}
		return ticketUser;
	}

	@GetMapping("/ticket/getPendingOfUser")
	public List<Ticket> getPendingTicketsOfUser(@RequestParam("userId") String uuid) {
		return ticketRepo.findPendingOfUser(uuid);
	}
	
	@GetMapping("/ticket/ticketUserDto")
	public List<TicketUserDto> getTicketUser(){
		List<Ticket> tickets = ticketRepo.findAll();
		List<TicketUserDto> ticketUser = new ArrayList<>();
		for(Ticket t : tickets) {
			TicketUserDto dto = new TicketUserDto();
			dto.setId(t.getId());
			dto.setCreatedAt(t.getCreatedAt());
			dto.setNotes(t.getNotes());
			dto.setStatus(t.getTicketStatus().getDescription());
			dto.setTicketStatus(t.getTicketStatus());
			dto.setTitle(t.getTitle());
			dto.setUpdatedAt(t.getUpdatedAt());
			dto.setUser(t.getUser());
			dto.setUserName(t.getUser().getUsername());
			ticketUser.add(dto);
		}
		return ticketUser;
	}

	@PutMapping("/ticket/updateTicket")
	public Ticket updateTicketStatus(@RequestBody Ticket t) {
		Ticket tick = ticketRepo.findById(t.getId()).orElseThrow(() -> new RuntimeException("ticket not found."));
		tick.setUpdatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		tick.setTicketStatus(t.getTicketStatus());
		ticketRepo.save(tick);
		return tick;
	}

	@PostMapping("/ticket/addNew")
	public Ticket addNewTicket(@RequestBody Ticket t) {
		Ticket tick = new Ticket();
		tick.setCreatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		tick.setNotes(t.getNotes());
		tick.setTicketStatus(t.getTicketStatus());
		tick.setTitle(t.getTitle());
		tick.setUpdatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		tick.setUser(t.getUser());
		ticketRepo.save(tick);
		return tick;
	}

}
