package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;

import app.spotify.spotifybe.model.Product;
import app.spotify.spotifybe.model.ProductStatus;

public class ProductAccountsDto {
	
	private int id;
	private Date createdAt;
	private Date deliveryTime;
	private String description;
	private String format;
	private int gate;
	private int maximum;
	private int minimum;
	private BigDecimal price;
	private byte[] productImage;
	private int sort;
	private String title;
	private String warranty;
	private ProductStatus status;
	
	private int nrOfAccounts;
	
	public ProductAccountsDto() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public int getNrOfAccounts() {
		return nrOfAccounts;
	}
	public void setNrOfAccounts(int nrOfAccounts) {
		this.nrOfAccounts = nrOfAccounts;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

}
