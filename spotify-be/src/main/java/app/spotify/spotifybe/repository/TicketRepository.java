package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{

	List<Ticket> findByUserId(String uuid);

}
