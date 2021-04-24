package app.spotify.spotifybe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.spotify.spotifybe.dto.AccountProductDto;
import app.spotify.spotifybe.dto.OrderDto;
import app.spotify.spotifybe.dto.OrderUserProdDto;
import app.spotify.spotifybe.exception.BalanceNotEnoughException;
import app.spotify.spotifybe.model.Account;
import app.spotify.spotifybe.model.Filter;
import app.spotify.spotifybe.model.Order;
import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.model.User;
import app.spotify.spotifybe.repository.AccountRepository;
import app.spotify.spotifybe.repository.FilterRepository;
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

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	FilterRepository filterRepo;

	@GetMapping("/order/getAll")
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@GetMapping("/order/getAllDto")
	public List<OrderUserProdDto> getAllDto() {
		List<Order> orders = orderRepo.findAll();
		List<OrderUserProdDto> orderUserProd = new ArrayList<>();
		for (Order o : orders) {
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
	public OrderDto getOrderById(@RequestParam("orderId") long orId) {
		Order o = orderRepo.findById(orId).orElseThrow(() -> new RuntimeException("Cannot find the requested order."));
		OrderDto dto = new OrderDto();
		dto.setId(o.getId());
		dto.setOrderDate(o.getOrderDate());
		dto.setQuantity(o.getQuantity());
		dto.setUser(o.getUser());
		dto.setValue(o.getValue());
		dto.setFilters(o.getFilters().size());
		dto.setProduct(o.getProduct());
		dto.setOrderStatus(o.getOrderStatus().getDescription());
		return dto;
	}

	@GetMapping("/order/getAllOfUser")
	public List<OrderUserProdDto> getAllOrdersOfUser(@RequestParam(name = "uuid") String uuid) {
		User user = userRepo.findById(uuid).orElseThrow(() -> new RuntimeException("User not found."));
		List<Order> orders = orderRepo.findByUserId(user.getId());
		List<OrderUserProdDto> orderUserProd = new ArrayList<>();
		for (Order o : orders) {
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

	@GetMapping("/order/downloadOrderAccounts")
	public ResponseEntity<Resource> downloadOrderAccounts(@RequestParam("orderId") long orderId,
			 HttpServletResponse response) throws Exception {

		Order o = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Cannot find the requested order."));
		List<Filter> filters = o.getFilters();
		List<Account> accounts = new ArrayList<>();
		List<Account> subList = new ArrayList<>();
		// apply quantity to accounts list dhe check sa her jan shit
		if (filters != null && !filters.isEmpty()) {
			if (filters.size() == 2) {
				accounts = accountRepo.findByCountryAndSubscriptionTypeAndProductId(filters.get(0).getFilterValue(),
						filters.get(1).getFilterValue(), o.getProduct().getId());
			} else if (filters.size() == 1) {
			
				switch (filters.get(0).getDescription()) {
				case "country":
					accounts = accountRepo.findByCountryAndProductId(filters.get(0).getFilterValue(),
							o.getProduct().getId());
					break;
				case "subscription":
					accounts = accountRepo.findBySubscriptionTypeAndProductId(filters.get(0).getFilterValue(),
							o.getProduct().getId());
					break;
				default:
					throw new Exception("The given filter is not correct.");
				}

			} else
				throw new Exception("Something went wrong with your order's filters.");
		} else {
			accounts = accountRepo.getAllAccountsOfOrder(orderId, o.getQuantity());
		}

		if (accounts.isEmpty()) {
			throw new Exception("No accounts were found for your order.");
		} else {
			for (Account a : accounts) {
				int soldToUser = accountRepo.findAccountSoldToUser(a.getId(), o.getUser().getId());
				if (a.getSold() < 2 && soldToUser != 1 && subList.size() < o.getQuantity()) {
					subList.add(a);
				}
			}
		}
		File file = new File("files/accounts" + orderId + ".txt"); // new File("/some/absolute/path/myfile.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		for (Account a : subList) {
			String newline = "";
			newline += a.getCredentials() != null ? a.getCredentials() + " | " : "";
			newline += a.getSubscriptionType() != null ? a.getSubscriptionType() + " | " : "";
			newline += a.getCountry() != null ? a.getCountry() + " | " : "";
			newline += a.getInvites() != null ? a.getInvites() + " | " : "";
			newline += a.getAddress() != null ? a.getAddress() + " | " : "";
			newline += a.getInviteToken() != null ? a.getInviteToken() + " | " : "";
			newline += a.getExpire() != null ? a.getExpire() + " | " : "";
			newline += a.getExtra() != null ? a.getExtra() + " | " : "";

			pw.println(newline);
		}
		pw.close();

		FileSystemResource resource = new FileSystemResource("files/accounts" + orderId + ".txt");
		if (!resource.exists()) {
			throw new FileNotFoundException("File not found.");
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

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

		if (order.getUser().getBalance().compareTo(order.getValue()) == 1) { // mduket duhet ==1 //ishte >0
			orderRepo.save(o);
		} else {
			throw new BalanceNotEnoughException("You do not have enough balance to complete this order.");
		}

		return o;

	}

}
