package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {

	private long id;
	private Date orderDate;
	private int quantity;
	private BigDecimal value;
	//private List<FilterDto> filters;
	//private OrderStatusDto orderStatus;
	//private ProductDto product;
	//private UserDto user;
	
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
}
