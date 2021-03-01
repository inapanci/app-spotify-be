package app.spotify.spotifybe.dto;

public class PaymentMethodDto {
	
	private int id;
	private String description;
	//private List<TransactionDto> transaction;
	//private List<UserDto> user;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
