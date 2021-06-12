package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="description",length=500)
	private String description;

	@Column(name="is_read", columnDefinition = "char(1)", length=1)
	private String read;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private Ticket ticket;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public Message() {
	}

	public Message(long id, Date createdAt, String description, String read, Ticket ticket, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.description = description;
		this.read = read;
		this.ticket = ticket;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getRead() {
		return this.read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", createdAt=" + createdAt + ", description=" + description + ", read=" + read
				+ ", ticket=" + ticket + ", user=" + user + "]";
	}

}