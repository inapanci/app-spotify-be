package app.spotify.spotifybe.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserDto {

	private String id;

	private String username;

	private BigDecimal balance;

	private String email;
	
	private Date lastSignIn;
	
	private String notifications;

	private String password;

	private String role;
	
	private Date signUpDate;
	
	private String userStatus;
	
	private List<UserPromotionDto> userPromotions;

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

	public List<UserPromotionDto> getUserPromotions() {
		return userPromotions;
	}

	public void setUserPromotions(List<UserPromotionDto> userPromotions) {
		this.userPromotions = userPromotions;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
		
}
