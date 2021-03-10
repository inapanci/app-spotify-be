package app.spotify.spotifybe.dto;

public class DashboardDto {
	
	private double revenue;
	private int nrOfOrders;
	private int nrOfTickets;
	private int nrOfProducts;
	private int nrOfUsers;
	private int nrOfClients;
	private double clientBalance;
	private double conversionRate;
	
	
	public DashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public int getNrOfOrders() {
		return nrOfOrders;
	}
	public void setNrOfOrders(int nrOfOrders) {
		this.nrOfOrders = nrOfOrders;
	}
	public int getNrOfTickets() {
		return nrOfTickets;
	}
	public void setNrOfTickets(int nrOfTickets) {
		this.nrOfTickets = nrOfTickets;
	}
	public int getNrOfProducts() {
		return nrOfProducts;
	}
	public void setNrOfProducts(int nrOfProducts) {
		this.nrOfProducts = nrOfProducts;
	}
	public int getNrOfUsers() {
		return nrOfUsers;
	}
	public void setNrOfUsers(int nrOfUsers) {
		this.nrOfUsers = nrOfUsers;
	}
	public int getNrOfClients() {
		return nrOfClients;
	}
	public void setNrOfClients(int nrOfClients) {
		this.nrOfClients = nrOfClients;
	}
	public double getClientBalance() {
		return clientBalance;
	}
	public void setClientBalance(double clientBalance) {
		this.clientBalance = clientBalance;
	}
	public double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	

}
