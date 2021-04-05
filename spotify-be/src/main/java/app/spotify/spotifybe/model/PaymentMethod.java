package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the payment_methods database table.
 * 
 */
@Entity
@Table(name = "payment_methods")
@NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "description", length = 100)
	private String description;

	@Column(name = "status", length = 45)
	private String status;

	@Column(name = "status_new_users", length = 45)
	private String statusNewUsers;

	@Column(name = "maximum")
	private Integer maximum;

	@Column(name = "minimum")
	private Integer minimum;

	@Column(name = "details", length = 150)
	private String details;

//	//bi-directional many-to-one association to Transaction
//	@OneToMany(mappedBy="paymentMethod")
//	private List<Transaction> transactions;
//
//	//bi-directional many-to-many association to User
//	@ManyToMany(mappedBy="paymentMethods")
//	private List<User> users;

	public PaymentMethod() {
	}

	public PaymentMethod(int id, String description, String status, String statusNewUsers, Integer maximum, Integer minimum,
			String details) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.statusNewUsers = statusNewUsers;
		this.maximum = maximum;
		this.minimum = minimum;
		this.details = details;
	}

	public int getId() {
		return this.id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusNewUsers() {
		return statusNewUsers;
	}

	public void setStatusNewUsers(String statusNewUsers) {
		this.statusNewUsers = statusNewUsers;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

//	public List<Transaction> getTransactions() {
//		return this.transactions;
//	}
//
//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}

//	public Transaction addTransaction(Transaction transaction) {
//		getTransactions().add(transaction);
//		transaction.setPaymentMethod(this);
//
//		return transaction;
//	}
//
//	public Transaction removeTransaction(Transaction transaction) {
//		getTransactions().remove(transaction);
//		transaction.setPaymentMethod(null);
//
//		return transaction;
//	}
//
//	public List<User> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

}