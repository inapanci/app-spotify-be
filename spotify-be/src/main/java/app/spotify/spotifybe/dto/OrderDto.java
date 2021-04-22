package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {

	private long id;
	private Date orderDate;
	private int quantity;
	private BigDecimal value;
	private Integer filters;
	private String orderStatus;
	private Integer productId;
	private String userId;
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getFilters() {
		return filters;
	}
	public void setFilters(Integer filters) {
		this.filters = filters;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
