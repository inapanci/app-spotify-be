package app.spotify.spotifybe.dto;

import java.util.List;

import app.spotify.spotifybe.model.Filter;
import app.spotify.spotifybe.model.Product;

public class AccountProductDto {

	private int quantity;
	private List<String> subscriptionTypes;
	private List<String> countries;
	private List<String> formats;
	private int stock;
	
	private List<Filter> filters;
	private Product product;
		
	public AccountProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<String> getSubscriptionTypes() {
		return subscriptionTypes;
	}

	public void setSubscriptionTypes(List<String> subscriptionTypes) {
		this.subscriptionTypes = subscriptionTypes;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getFormats() {
		return formats;
	}

	public void setFormats(List<String> formats) {
		this.formats = formats;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
}
