package app.spotify.spotifybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.DashboardDto;
import app.spotify.spotifybe.dto.OrderDashboardDto;
import app.spotify.spotifybe.dto.TicketDashboardDto;
import app.spotify.spotifybe.repository.AccountRepository;
import app.spotify.spotifybe.repository.MessageRepository;
import app.spotify.spotifybe.repository.OrderRepository;
import app.spotify.spotifybe.repository.ProductRepository;
import app.spotify.spotifybe.repository.TicketRepository;
import app.spotify.spotifybe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class DashboardController {
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MessageRepository messageRepo;
	
	
	@GetMapping("/dashboard/getAll")
	public DashboardDto getDashboard() {
		DashboardDto dash = new DashboardDto();
		dash.setRevenue(orderRepo.calculateRevenue());
		dash.setNrOfOrders(orderRepo.findAll().size());
		dash.setNrOfTickets(ticketRepo.findAll().size());
		dash.setNrOfProducts(productRepo.findAll().size());
		dash.setNrOfUsers(userRepo.findAll().size());
		dash.setNrOfClients(orderRepo.findClients());
		dash.setClientBalance(userRepo.getAllBalance());
		dash.setConversionRate((dash.getNrOfClients()/dash.getRevenue())*100);
		return dash;
	}
	
	@GetMapping("/dashboard/getOrdersInDash")
	public OrderDashboardDto getOrderDashboard() {
		OrderDashboardDto dto = new OrderDashboardDto();
		dto.setCanceledOrders(orderRepo.findByOrderStatusDescription("canceled").size());
		dto.setCompletedOrders(orderRepo.findByOrderStatusDescription("completed").size());
		dto.setProcessingOrders(orderRepo.findByOrderStatusDescription("processing").size());
		dto.setReplacements(orderRepo.findByOrderStatusDescription("replacement").size());
		dto.setAllOrders(orderRepo.findAll().size());
		return dto;
	}
	
	@GetMapping("/dashboard/getTicketsInDash")
	public TicketDashboardDto getTicketsDashboard() {
		TicketDashboardDto dto = new TicketDashboardDto();
		dto.setClosedTickets(ticketRepo.findByTicketStatusDescription("closed").size());
		dto.setOpenedTickets(ticketRepo.findByTicketStatusDescription("opened").size());
		dto.setPendingTickets(ticketRepo.findByTicketStatusDescription("pending").size());
		dto.setNewReplies(messageRepo.getNewReplies().size());
		dto.setAllTickets(ticketRepo.findAll().size());
		return dto;
	}
	

}
