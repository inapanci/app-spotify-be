package app.spotify.spotifybe.dto;

public class OrderDashboardDto {
	
	private int completedOrders;
	private int completedOfUsers;
	private int allOrders;
	private int allOfUsers;
	private int processingOrders;
	private int processingOfUsers;
	private int replacements;
	private int replacementsOfUsers;
	private int canceledOrders;
	private int canceledOfUsers;
	
	public OrderDashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCompletedOrders() {
		return completedOrders;
	}
	public void setCompletedOrders(int completedOrders) {
		this.completedOrders = completedOrders;
	}
	public int getProcessingOrders() {
		return processingOrders;
	}
	public void setProcessingOrders(int processingOrders) {
		this.processingOrders = processingOrders;
	}
	public int getReplacements() {
		return replacements;
	}
	public void setReplacements(int replacements) {
		this.replacements = replacements;
	}
	public int getCanceledOrders() {
		return canceledOrders;
	}
	public void setCanceledOrders(int canceledOrders) {
		this.canceledOrders = canceledOrders;
	}
	public int getAllOrders() {
		return allOrders;
	}
	public void setAllOrders(int allOrders) {
		this.allOrders = allOrders;
	}
	public int getAllOfUsers() {
		return allOfUsers;
	}
	public void setAllOfUsers(int allOfUsers) {
		this.allOfUsers = allOfUsers;
	}
	public int getCompletedOfUsers() {
		return completedOfUsers;
	}
	public void setCompletedOfUsers(int completedOfUsers) {
		this.completedOfUsers = completedOfUsers;
	}
	public int getProcessingOfUsers() {
		return processingOfUsers;
	}
	public void setProcessingOfUsers(int processingOfUsers) {
		this.processingOfUsers = processingOfUsers;
	}
	public int getReplacementsOfUsers() {
		return replacementsOfUsers;
	}
	public void setReplacementsOfUsers(int replacementsOfUsers) {
		this.replacementsOfUsers = replacementsOfUsers;
	}
	public int getCanceledOfUsers() {
		return canceledOfUsers;
	}
	public void setCanceledOfUsers(int canceledOfUsers) {
		this.canceledOfUsers = canceledOfUsers;
	}

	
}
