package app.spotify.spotifybe.dto;

import java.util.Date;

public class AnnouncementDto {
	private int id;
	private Date createdAt;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
