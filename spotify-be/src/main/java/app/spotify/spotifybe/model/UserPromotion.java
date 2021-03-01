package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_promotion database table.
 * 
 */
@Entity
@Table(name="user_promotion")
@NamedQuery(name="UserPromotion.findAll", query="SELECT u FROM UserPromotion u")
public class UserPromotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public UserPromotion() {
	}

	public UserPromotion(int id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserPromotion [id=" + id + ", description=" + description + ", user=" + user + "]";
	}

}