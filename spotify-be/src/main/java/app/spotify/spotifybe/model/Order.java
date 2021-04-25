package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date")
	private Date orderDate;

	private int quantity;

	private BigDecimal value;

	//bi-directional many-to-many association to Filter
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
		name="order_filters"
		, joinColumns={
			@JoinColumn(name="order_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="filter_id")
			}
		)
	private List<Filter> filters;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="order_status_id")
	private OrderStatus orderStatus;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public Order() {
	}

	public Order(long id, Date orderDate, int quantity, BigDecimal value, List<Filter> filters,
			OrderStatus orderStatus, Product product, User user) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.value = value;
		this.filters = filters;
		this.orderStatus = orderStatus;
		this.product = product;
		this.user = user;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public List<Filter> getFilters() {
		return this.filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", quantity=" + quantity + ", value=" + value
				+ ", filters=" + filters + ", orderStatus=" + orderStatus + ", product=" + product + ", user=" + user
				+ "]";
	}

}