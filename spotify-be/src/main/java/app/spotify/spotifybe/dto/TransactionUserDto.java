package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;

import app.spotify.spotifybe.model.PaymentMethod;
import app.spotify.spotifybe.model.TransactionStatus;
import app.spotify.spotifybe.model.User;

public class TransactionUserDto {
	
	private long id;
	private BigDecimal amount;
	private Date createdAt;
	private String description;
	private String transactionId;
	private String paymentMethod;
	private String transactionStatus;
	private String usersUsername;
	private String usersEmail;
	private User user;
	
	
	public TransactionUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getUsersUsername() {
		return usersUsername;
	}
	public void setUsersUsername(String usersUsername) {
		this.usersUsername = usersUsername;
	}
	public String getUsersEmail() {
		return usersEmail;
	}
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
