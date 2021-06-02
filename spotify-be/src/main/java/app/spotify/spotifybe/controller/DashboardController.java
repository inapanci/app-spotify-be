package app.spotify.spotifybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.DashboardDto;
import app.spotify.spotifybe.dto.OrderDashboardDto;
import app.spotify.spotifybe.dto.TicketDashboardDto;
import app.spotify.spotifybe.dto.UserDashboardDto;
import app.spotify.spotifybe.model.User;
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
		if(orderRepo.calculateRevenue()!=null) {
			dash.setRevenue(orderRepo.calculateRevenue());
		}else dash.setRevenue(0.00);
		
		
		dash.setNrOfOrders(orderRepo.findAll().size());
		dash.setNrOfTickets(ticketRepo.findAll().size());
		dash.setNrOfProducts(productRepo.findAll().size());
		dash.setNrOfUsers(userRepo.findAll().size());
		if(orderRepo.findClients()!=null && orderRepo.findClients()!=0) {
			dash.setNrOfClients(orderRepo.findClients());
			dash.setConversionRate(((double)dash.getNrOfUsers()/dash.getNrOfClients())*100);
		}
		else dash.setConversionRate(0);
		if(userRepo.getAllBalance()!=null) {
			dash.setClientBalance(userRepo.getAllBalance());
		}
	
		
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
	
	@GetMapping("/dashboard/usersOrders")
	public OrderDashboardDto getUsersOrdersForDashboard(@RequestParam("usersId") String uuid) {
		OrderDashboardDto dto = new OrderDashboardDto();
		dto.setAllOfUsers(orderRepo.findByUserId(uuid).size());
		dto.setCanceledOfUsers(orderRepo.findByUserIdAndOrderStatusDescription(uuid, "canceled").size());
		dto.setCompletedOfUsers(orderRepo.findByUserIdAndOrderStatusDescription(uuid, "completed").size());
		dto.setProcessingOfUsers(orderRepo.findByUserIdAndOrderStatusDescription(uuid, "processing").size());
		dto.setReplacementsOfUsers(orderRepo.findByUserIdAndOrderStatusDescription(uuid, "replacement").size());
		return dto;
	}
	
	@GetMapping("/dashboard/usersTickets")
	public TicketDashboardDto getUsersTicketsForDashboard(@RequestParam("usersId") String uuid) {
		TicketDashboardDto dto = new TicketDashboardDto();
		dto.setClosedOfUsers(ticketRepo.findByUserIdAndTicketStatusDescription(uuid,"closed").size());
		dto.setOpenedOfUsers(ticketRepo.findByUserIdAndTicketStatusDescription(uuid, "opened").size());
		dto.setPendingOfUsers(ticketRepo.findByUserIdAndTicketStatusDescription(uuid, "pending").size());
		dto.setNewRepliesOfUsers(messageRepo.getNewRepliesOfUser(uuid).size());
		dto.setAllTicketsOfUser(ticketRepo.findByUserId(uuid).size());
		return dto;
	}

}
