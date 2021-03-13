package app.spotify.spotifybe.dto;

public class TicketDashboardDto {
	
	private int openedTickets;
	private int pendingTickets;
	private int newReplies;
	private int closedTickets;
	private int allTickets;
	
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
	
}
