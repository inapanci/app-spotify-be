package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.AccountProductDto;
import app.spotify.spotifybe.dto.OrderUserProdDto;
import app.spotify.spotifybe.exception.BalanceNotEnoughException;
import app.spotify.spotifybe.model.Order;
import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.AccountRepository;
import app.spotify.spotifybe.repository.OrderRepository;
import app.spotify.spotifybe.repository.OrderStatusRepository;
import app.spotify.spotifybe.repository.ProductRepository;
import app.spotify.spotifybe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderStatusRepository oStatusRepo;
	
	@Autowired
	UserRepository userRepo;

	
	@GetMapping("/order/getAll")
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}
	
	@GetMapping("/order/getAllDto")
	public List<OrderUserProdDto> getAllDto(){
		List<Order> orders = orderRepo.findAll();
		List<OrderUserProdDto> orderUserProd = new ArrayList<>();
		for(Order o : orders) {
			OrderUserProdDto dto = new OrderUserProdDto();
			dto.setId(o.getId());
			dto.setOrderDate(o.getOrderDate());
			dto.setProductName(o.getProduct().getDescription());
			dto.setQuantity(o.getQuantity());
			dto.setUser(o.getUser());
			dto.setUserEmail(o.getUser().getEmail());
			dto.setUserName(o.getUser().getUsername());
			dto.setValue(o.getValue());
			dto.setOrderStatus(o.getOrderStatus().getDescription());
			orderUserProd.add(dto);
		}
		return orderUserProd;
	}
	
	@GetMapping("/order/getById")
	public Order getOrderById(@RequestParam("orderId") long orId) {
		return orderRepo.findById(orId).orElseThrow(() -> new RuntimeException("Cannot find this order."));
	}
	
	@GetMapping("/order/getAllOfUser")
	public List<Order> getAllOrdersOfUser(@RequestParam(name="uuid") String uuid){
		User user = userRepo.findById(uuid).orElseThrow(()->new RuntimeException("user not found"));
		List<Order> orders = orderRepo.findByUserId(user.getId());
		return orders;
		
	}
	
	@PostMapping("/order/addNew")
	public Order addNewOrder(@RequestBody Order order) throws BalanceNotEnoughException {
		Order o = new Order();
		o.setOrderDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		o.setOrderStatus(oStatusRepo.findByDescription("ready"));
		o.setProduct(order.getProduct());
		o.setQuantity(order.getQuantity());
		o.setUser(order.getUser());
		o.setValue(order.getValue());
		o.setFilters(order.getFilters());
		
		if(order.getUser().getBalance().compareTo(order.getValue()) > 0) {
			orderRepo.save(o);
		}
		else {
			throw new BalanceNotEnoughException("You do not have enough balance to complete this order.");
		}
				
		return o;
		
	}
	
}
