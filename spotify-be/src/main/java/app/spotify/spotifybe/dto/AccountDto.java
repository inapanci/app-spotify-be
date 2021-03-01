package app.spotify.spotifybe.dto;

import java.util.Date;

public class AccountDto {

	private long id;
	private String address;
	private String country;
	private String credentials;
	private Date expire;
	private String extra;
	private String inviteToken;
	private String invites;
	private int sold;
	private String subscriptionType;
	//private ProductDto product;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getInviteToken() {
		return inviteToken;
	}
	public void setInviteToken(String inviteToken) {
		this.inviteToken = inviteToken;
	}
	public String getInvites() {
		return invites;
	}
	public void setInvites(String invites) {
		this.invites = invites;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
}
