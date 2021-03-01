package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the announcements database table.
 * 
 */
@Entity
@Table(name="announcements")
@NamedQuery(name="Announcement.findAll", query="SELECT a FROM Announcement a")
public class Announcement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="description", length=200)
	private String description;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_by_uuid")
	private User user;

	public Announcement() {
	}

	public Announcement(int id, Date createdAt, String description, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.description = description;
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Announcement [id=" + id + ", createdAt=" + createdAt + ", description=" + description + ", user=" + user
				+ "]";
	}

}