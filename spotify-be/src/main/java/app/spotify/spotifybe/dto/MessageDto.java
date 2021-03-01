package app.spotify.spotifybe.dto;

import java.util.Date;

import app.spotify.spotifybe.model.Ticket;

public class MessageDto {

	private long id;
	private Date createdAt;
	private String description;
	private String read;
	private Ticket ticket;
	//private UserDto user;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
