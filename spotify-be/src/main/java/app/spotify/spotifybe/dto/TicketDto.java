package app.spotify.spotifybe.dto;

import java.util.Date;
import java.util.List;

import app.spotify.spotifybe.model.Message;
import app.spotify.spotifybe.model.TicketStatus;

public class TicketDto {
	
	private int id;
	private Date createdAt;

	private String notes;

	private String title;
	private Date updatedAt;
	private List<MessageDto> messages;
	private TicketStatus ticketStatus;
	private Integer inQueue;
	private Integer supportOnline;
	private String priority;
	//private UserDto user;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<MessageDto> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}
	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public Integer getInQueue() {
		return inQueue;
	}
	public void setInQueue(Integer inQueue) {
		this.inQueue = inQueue;
	}
	public Integer getSupportOnline() {
		return supportOnline;
	}
	public void setSupportOnline(Integer supportOnline) {
		this.supportOnline = supportOnline;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
