package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	
	List<Message> findByTicketId(int ticketId);

}
