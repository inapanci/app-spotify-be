package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.TicketStatus;

public interface TicketStatusRepository extends JpaRepository<TicketStatus,Integer>{

	TicketStatus findByDescription(String descr);

}
