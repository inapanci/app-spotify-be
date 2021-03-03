package app.spotify.spotifybe.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.model.Order;
import app.spotify.spotifybe.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spotify")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepo;
	
	@GetMapping("/order/getAll")
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}
	
	@GetMapping("/order/getById")
	public Order getOrderById(@RequestParam("orderId") long orId) {
		return orderRepo.findById(orId).orElseThrow(() -> new RuntimeException("Cannot find this order."));
	}
	
	@PostMapping("/order/addNew")
	public Order addNewOrder(@RequestBody Order order) {
		Order o = new Order();
		o.setOrderDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		o.setOrderStatus(order.getOrderStatus());
		o.setProduct(order.getProduct());
		o.setQuantity(order.getQuantity());
		o.setUser(order.getUser());
		o.setValue(order.getValue());
		o.setFilters(order.getFilters());
		
		orderRepo.save(o);
		return o;
		
	}
}
