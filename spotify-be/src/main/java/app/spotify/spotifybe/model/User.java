package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uuid", nullable = false)
	private String id;

	private String username;

	private BigDecimal balance;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_sign_in")
	private Date lastSignIn;

	@Column(name = "notifications", columnDefinition = "char(1)")
	private String notifications;

	private String password;

	private String role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sign_up_date")
	private Date signUpDate;

	// bi-directional many-to-many association to Account
//	@ManyToMany
//	@JoinTable(name = "account_buyers", joinColumns = { @JoinColumn(name = "buyer_uuid") }, inverseJoinColumns = {
//			@JoinColumn(name = "account_id") })
//	private List<Account> accounts;

//	// bi-directional many-to-one association to Announcement
//	@OneToMany(mappedBy = "user")
//	private List<Announcement> announcements;

	// bi-directional many-to-one association to CustomProductPrice
//	@OneToMany(mappedBy = "user")
//	private List<CustomProductPrice> customProductPrices;

//	// bi-directional many-to-one association to Message
//	@OneToMany(mappedBy = "user")
//	private List<Message> messages;

//	// bi-directional many-to-one association to Order
//	@OneToMany(mappedBy = "user")
//	private List<Order> orders;

//	// bi-directional many-to-one association to Staff
//	@OneToMany(mappedBy = "user")
//	private List<Staff> staffs;

//	// bi-directional many-to-one association to Ticket
//	@OneToMany(mappedBy = "user")
//	private List<Ticket> tickets;

//	// bi-directional many-to-one association to Transaction
//	@OneToMany(mappedBy = "user")
//	private List<Transaction> transactions;

	// bi-directional many-to-one association to UserPromotion
	@OneToMany(mappedBy = "user")
	private List<UserPromotion> userPromotions;

	// bi-directional many-to-one association to UserStatus
	@ManyToOne
	@JoinColumn(name = "user_status_id")
	private UserStatus userStatus;

	// bi-directional many-to-many association to PaymentMethod
	@ManyToMany
	@JoinTable(name = "users_payment_methods", joinColumns = { @JoinColumn(name = "uuid") }, inverseJoinColumns = {
			@JoinColumn(name = "payment_method_id") })
	private List<PaymentMethod> paymentMethods;

	public User() {
	}

	public User(String id, String username, BigDecimal balance, String email, Date lastSignIn, String notifications,
			String password, String role, Date signUpDate, //List<Account> accounts, //List<Announcement> announcements,
			//List<CustomProductPrice> customProductPrices, //List<Order> orders,
			//List<Staff> staffs, //List<Ticket> tickets, //List<Transaction> transactions,, List<Message> messages, 
			List<UserPromotion> userPromotions, UserStatus userStatus, List<PaymentMethod> paymentMethods) {
		super();
		this.id = id;
		this.username = username;
		this.balance = balance;
		this.email = email;
		this.lastSignIn = lastSignIn;
		this.notifications = notifications;
		this.password = password;
		this.role = role;
		this.signUpDate = signUpDate;
		//this.accounts = accounts;
		//this.announcements = announcements;
		//this.customProductPrices = customProductPrices;
		//this.messages = messages;
		//this.orders = orders;
		//this.staffs = staffs;
		//this.tickets = tickets;
		//this.transactions = transactions;
		this.userPromotions = userPromotions;
		this.userStatus = userStatus;
		this.paymentMethods = paymentMethods;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastSignIn() {
		return lastSignIn;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

//	public List<Announcement> getAnnouncements() {
//		return announcements;
//	}
//
//	public void setAnnouncements(List<Announcement> announcements) {
//		this.announcements = announcements;
//	}

//	public List<CustomProductPrice> getCustomProductPrices() {
//		return customProductPrices;
//	}
//
//	public void setCustomProductPrices(List<CustomProductPrice> customProductPrices) {
//		this.customProductPrices = customProductPrices;
//	}
//
//	public List<Message> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(List<Message> messages) {
//		this.messages = messages;
//	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

//	public List<Staff> getStaffs() {
//		return staffs;
//	}
//
//	public void setStaffs(List<Staff> staffs) {
//		this.staffs = staffs;
//	}

//	public List<Ticket> getTickets() {
//		return tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}

//	public List<Transaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}

	public List<UserPromotion> getUserPromotions() {
		return userPromotions;
	}

	public void setUserPromotions(List<UserPromotion> userPromotions) {
		this.userPromotions = userPromotions;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", balance=" + balance + ", email=" + email
				+ ", lastSignIn=" + lastSignIn + ", notifications=" + notifications + ", password=" + password
				+ ", role=" + role + ", signUpDate=" + signUpDate + ", userPromotions=" + userPromotions + ", userStatus="
				+ userStatus + ", paymentMethods=" + paymentMethods + "]";
	}

}