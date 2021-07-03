package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Order;
import app.spotify.spotifybe.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{

	List<Ticket> findByUserId(String uuid);

	@Query("Select t from Ticket t where t.user.id = ?1 and t.ticketStatus.description = 'pending'")
	List<Ticket> findPendingOfUser(String uuid);

	List<Ticket> findByTicketStatusDescription(String descr);

	List<Ticket> findByUserIdAndTicketStatusDescription(String uuid, String descr);

	List<Ticket> findByUserIdAndTicketStatusDescriptionNotLike(String uuid, String descr);

}
