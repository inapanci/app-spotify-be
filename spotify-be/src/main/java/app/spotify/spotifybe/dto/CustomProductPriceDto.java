package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CustomProductPriceDto {
	
	private int id;
	private Date startDate;
	private Date endDate;
	private BigDecimal price;
	//private ProductDto product;
	//private UserDto user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
