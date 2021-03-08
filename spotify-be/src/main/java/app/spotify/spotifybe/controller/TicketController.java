package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
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

	@GetMapping("/ticket/getPendingOfUser")
	public List<Ticket> getPendingTicketsOfUser(@RequestParam("userId") String uuid) {
		return ticketRepo.findPendingOfUser(uuid);
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
