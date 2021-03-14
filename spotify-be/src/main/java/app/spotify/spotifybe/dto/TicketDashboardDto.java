package app.spotify.spotifybe.dto;

public class TicketDashboardDto {
	
	private int openedTickets;
	private int openedOfUsers;
	private int pendingTickets;
	private int pendingOfUsers;
	private int newReplies;
	private int newRepliesOfUsers;
	private int closedTickets;
	private int closedOfUsers;
	private int allTickets;
	private int allTicketsOfUser;
	
	public TicketDashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOpenedTickets() {
		return openedTickets;
	}

	public void setOpenedTickets(int openedTickets) {
		this.openedTickets = openedTickets;
	}

	public int getPendingTickets() {
		return pendingTickets;
	}

	public void setPendingTickets(int pendingTickets) {
		this.pendingTickets = pendingTickets;
	}

	public int getNewReplies() {
		return newReplies;
	}

	public void setNewReplies(int newReplies) {
		this.newReplies = newReplies;
	}

	public int getClosedTickets() {
		return closedTickets;
	}

	public void setClosedTickets(int closedTickets) {
		this.closedTickets = closedTickets;
	}

	public int getAllTickets() {
		return allTickets;
	}

	public void setAllTickets(int allTickets) {
		this.allTickets = allTickets;
	}

	public int getOpenedOfUsers() {
		return openedOfUsers;
	}

	public void setOpenedOfUsers(int openedOfUsers) {
		this.openedOfUsers = openedOfUsers;
	}

	public int getPendingOfUsers() {
		return pendingOfUsers;
	}

	public void setPendingOfUsers(int pendingOfUsers) {
		this.pendingOfUsers = pendingOfUsers;
	}

	public int getNewRepliesOfUsers() {
		return newRepliesOfUsers;
	}

	public void setNewRepliesOfUsers(int newRepliesOfUsers) {
		this.newRepliesOfUsers = newRepliesOfUsers;
	}

	public int getClosedOfUsers() {
		return closedOfUsers;
	}

	public void setClosedOfUsers(int closedOfUsers) {
		this.closedOfUsers = closedOfUsers;
	}

	public int getAllTicketsOfUser() {
		return allTicketsOfUser;
	}

	public void setAllTicketsOfUser(int allTicketsOfUser) {
		this.allTicketsOfUser = allTicketsOfUser;
	}
	
}
