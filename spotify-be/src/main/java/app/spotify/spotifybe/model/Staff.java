package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="online", columnDefinition = "char(1)")
	private String online;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public Staff() {
	}

	public Staff(int id, String online, User user) {
		super();
		this.id = id;
		this.online = online;
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOnline() {
		return this.online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", online=" + online + ", user=" + user + "]";
	}

}
