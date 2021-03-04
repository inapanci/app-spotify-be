package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ticket_status database table.
 * 
 */
@Entity
@Table(name="ticket_status")
@NamedQuery(name="TicketStatus.findAll", query="SELECT t FROM TicketStatus t")
public class TicketStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="description", length=100)
	private String description;

//	//bi-directional many-to-one association to Ticket
//	@OneToMany(mappedBy="ticketStatus")
//	private List<Ticket> tickets;

	public TicketStatus() {
	}

	public TicketStatus(int id, String description) {
		super();
		this.id = id;
		this.description = description;
		//this.tickets = tickets;
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

//	public List<Ticket> getTickets() {
//		return this.tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}

//	public Ticket addTicket(Ticket ticket) {
//		getTickets().add(ticket);
//		ticket.setTicketStatus(this);
//
//		return ticket;
//	}
//
//	public Ticket removeTicket(Ticket ticket) {
//		getTickets().remove(ticket);
//		ticket.setTicketStatus(null);
//
//		return ticket;
//	}

	@Override
	public String toString() {
		return "TicketStatus [id=" + id + ", description=" + description + "]";
	}

}
