package app.spotify.spotifybe.dto;

import java.math.BigDecimal;

public class FilterDto {

	private int id;
	private String description;
	private BigDecimal price;
	//private List<OrderDto> orders;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
