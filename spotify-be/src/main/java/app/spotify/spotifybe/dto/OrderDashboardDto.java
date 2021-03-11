package app.spotify.spotifybe.dto;

public class OrderDashboardDto {
	
	private int completedOrders;
	private int processingOrders;
	private int replacements;
	private int canceledOrders;
	
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
	
}
