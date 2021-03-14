package app.spotify.spotifybe.dto;

import java.util.Date;

public class UserDashboardDto {
	
	private double amountSpent;
	private int totalOrders;
	private int accountsPurchased;
	private int totalTickets;
	private Date lastSignIn;
	private Date signUpDate;
	private double balance;
	private int staffOnline;
	
	public UserDashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(double amountSpent) {
		this.amountSpent = amountSpent;
	}
	public int getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}
	public int getAccountsPurchased() {
		return accountsPurchased;
	}
	public void setAccountsPurchased(int accountsPurchased) {
		this.accountsPurchased = accountsPurchased;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public Date getLastSignIn() {
		return lastSignIn;
	}
	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}
	public Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStaffOnline() {
		return staffOnline;
	}
	public void setStaffOnline(int staffOnline) {
		this.staffOnline = staffOnline;
	}

}

