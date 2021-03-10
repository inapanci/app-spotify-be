package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the payment_methods database table.
 * 
 */
@Entity
@Table(name="payment_methods")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT p FROM PaymentMethod p")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="description", length=100)
	private String description;

//	//bi-directional many-to-one association to Transaction
//	@OneToMany(mappedBy="paymentMethod")
//	private List<Transaction> transactions;
//
//	//bi-directional many-to-many association to User
//	@ManyToMany(mappedBy="paymentMethods")
//	private List<User> users;

	public PaymentMethod() {
	}

	public PaymentMethod(int id, String description) {
		super();
		this.id = id;
		this.description = description;
		//this.transactions = transactions;
		//this.users = users;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", description=" + description + "]";
	}

}