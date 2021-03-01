package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;

/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name = "accounts")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "country", length = 70)
	private String country;

	@Column(name = "credentials", length = 250, nullable = false)
	private String credentials;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire")
	private Date expire;

	@Column(name = "extra", length = 200)
	private String extra;

	@Column(name = "invite_token")
	private String inviteToken;

	@Column(name = "invites", length = 50)
	private String invites;

	@Column(name = "sold")
	private int sold;

	@Column(name = "subscription_type", length = 45)
	private String subscriptionType;

//	// bi-directional many-to-many association to User
//	@ManyToMany(mappedBy = "accounts")
//	private List<User> users;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Account() {
	}

	public Account(long id, String address, String country, String credentials, Date expire, String extra,
			String inviteToken, String invites, int sold, String subscriptionType, Product product) {
		super();
		this.id = id;
		this.address = address;
		this.country = country;
		this.credentials = credentials;
		this.expire = expire;
		this.extra = extra;
		this.inviteToken = inviteToken;
		this.invites = invites;
		this.sold = sold;
		this.subscriptionType = subscriptionType;
		//this.users = users;
		this.product = product;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCredentials() {
		return this.credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public Date getExpire() {
		return this.expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getExtra() {
		return this.extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getInviteToken() {
		return this.inviteToken;
	}

	public void setInviteToken(String inviteToken) {
		this.inviteToken = inviteToken;
	}

	public String getInvites() {
		return this.invites;
	}

	public void setInvites(String invites) {
		this.invites = invites;
	}

	public int getSold() {
		return this.sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", address=" + address + ", country=" + country + ", credentials=" + credentials
				+ ", expire=" + expire + ", extra=" + extra + ", inviteToken=" + inviteToken + ", invites=" + invites
				+ ", sold=" + sold + ", subscriptionType=" + subscriptionType + ", product=" + product + "]";
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

}