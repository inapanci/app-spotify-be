package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.model.User;

public class OrderDto {

	private long id;
	private Date orderDate;
	private int quantity;
	private BigDecimal value;
	private List<FilterDto> filters;
	private String orderStatus;
	private Product product;
	private User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<FilterDto> getFilters() {
		return filters;
	}
	public void setFilters(List<FilterDto> filters) {
		this.filters = filters;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
