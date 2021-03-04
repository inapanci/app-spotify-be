package app.spotify.spotifybe.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_time")
	private Date deliveryTime;

	private String description;

	private String format;

	private int gate;

	private int maximum;

	private int minimum;

	private BigDecimal price;

	@Lob
	@Column(name="product_image")
	private byte[] productImage;

	private int sort;

	private String title;

	private String warranty;

//	//bi-directional many-to-one association to Account
//	@OneToMany(mappedBy="product")
//	private List<Account> accounts;

	//bi-directional many-to-one association to CustomProductPrice
	@OneToMany(mappedBy="product")
	private List<CustomProductPrice> customProductPrices;

//	//bi-directional many-to-one association to Order
//	@OneToMany(mappedBy="product")
//	private List<Order> orders;

	//bi-directional many-to-one association to ProductStatus
	@ManyToOne
	@JoinColumn(name="status_id")
	private ProductStatus productStatus;

	public Product() {
	}

	public Product(int id, Date createdAt, Date deliveryTime, String description, String format, int gate, int maximum,
			int minimum, BigDecimal price, byte[] productImage, int sort, String title, String warranty,
			 List<CustomProductPrice> customProductPrices, //List<Order> orders, List<Account> accounts,
			ProductStatus productStatus) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.deliveryTime = deliveryTime;
		this.description = description;
		this.format = format;
		this.gate = gate;
		this.maximum = maximum;
		this.minimum = minimum;
		this.price = price;
		this.productImage = productImage;
		this.sort = sort;
		this.title = title;
		this.warranty = warranty;
		//this.accounts = accounts;
		this.customProductPrices = customProductPrices;
		//this.orders = orders;
		this.productStatus = productStatus;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getGate() {
		return this.gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public int getMaximum() {
		return this.maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return this.minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public byte[] getProductImage() {
		return this.productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWarranty() {
		return this.warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

//	public List<Account> getAccounts() {
//		return this.accounts;
//	}
//
//	public void setAccounts(List<Account> accounts) {
//		this.accounts = accounts;
//	}

//	public Account addAccount(Account account) {
//		getAccounts().add(account);
//		account.setProduct(this);
//
//		return account;
//	}
//
//	public Account removeAccount(Account account) {
//		getAccounts().remove(account);
//		account.setProduct(null);
//
//		return account;
//	}

	public List<CustomProductPrice> getCustomProductPrices() {
		return this.customProductPrices;
	}

	public void setCustomProductPrices(List<CustomProductPrice> customProductPrices) {
		this.customProductPrices = customProductPrices;
	}

	public CustomProductPrice addCustomProductPrice(CustomProductPrice customProductPrice) {
		getCustomProductPrices().add(customProductPrice);
		customProductPrice.setProduct(this);

		return customProductPrice;
	}

	public CustomProductPrice removeCustomProductPrice(CustomProductPrice customProductPrice) {
		getCustomProductPrices().remove(customProductPrice);
		customProductPrice.setProduct(null);

		return customProductPrice;
	}
//
//	public List<Order> getOrders() {
//		return this.orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

//	public Order addOrder(Order order) {
//		getOrders().add(order);
//		order.setProduct(this);
//
//		return order;
//	}
//
//	public Order removeOrder(Order order) {
//		getOrders().remove(order);
//		order.setProduct(null);
//
//		return order;
//	}

	public ProductStatus getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", createdAt=" + createdAt + ", deliveryTime=" + deliveryTime + ", description="
				+ description + ", format=" + format + ", gate=" + gate + ", maximum=" + maximum + ", minimum="
				+ minimum + ", price=" + price + ", productImage=" + Arrays.toString(productImage) + ", sort=" + sort
				+ ", title=" + title + ", warranty=" + warranty + ", customProductPrices=" + customProductPrices
				+ ", productStatus=" + productStatus + "]";
	}

}
