package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String description;

	@Column(name="transaction_id")
	private String transactionId;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_method_id")
	private PaymentMethod paymentMethod;

	//bi-directional many-to-one association to TransactionStatus
	@ManyToOne
	@JoinColumn(name="status_id")
	private TransactionStatus transactionStatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public Transaction() {
	}

	public Transaction(long id, BigDecimal amount, Date createdAt, String description, String transactionId,
			PaymentMethod paymentMethod, TransactionStatus transactionStatus, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.description = description;
		this.transactionId = transactionId;
		this.paymentMethod = paymentMethod;
		this.transactionStatus = transactionStatus;
		this.user = user;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public TransactionStatus getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", createdAt=" + createdAt + ", description="
				+ description + ", transactionId=" + transactionId + ", paymentMethod=" + paymentMethod
				+ ", transactionStatus=" + transactionStatus + ", user=" + user + "]";
	}

}