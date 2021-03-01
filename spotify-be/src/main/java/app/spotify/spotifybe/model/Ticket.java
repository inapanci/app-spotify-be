package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String notes;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="ticket")
	private List<Message> messages;

	//bi-directional many-to-one association to TicketStatus
	@ManyToOne
	@JoinColumn(name="ticket_status_id")
	private TicketStatus ticketStatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uuid")
	private User user;

	public Ticket() {
	}

	public Ticket(int id, Date createdAt, String notes, String title, Date updatedAt, List<Message> messages,
			TicketStatus ticketStatus, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.notes = notes;
		this.title = title;
		this.updatedAt = updatedAt;
		this.messages = messages;
		this.ticketStatus = ticketStatus;
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

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setTicket(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setTicket(null);

		return message;
	}

	public TicketStatus getTicketStatus() {
		return this.ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", createdAt=" + createdAt + ", notes=" + notes + ", title=" + title
				+ ", updatedAt=" + updatedAt + ", messages=" + messages + ", ticketStatus=" + ticketStatus + ", user="
				+ user + "]";
	}

}