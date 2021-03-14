package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	
	List<Message> findByTicketId(int ticketId);

	@Query("Select m From Message m Where m.read = 0")
	List<Message> getNewReplies();

	@Query("Select m From Message m Where m.read = 0 and m.user.id=?1")
	List<Message> getNewRepliesOfUser(String uuid);

}
