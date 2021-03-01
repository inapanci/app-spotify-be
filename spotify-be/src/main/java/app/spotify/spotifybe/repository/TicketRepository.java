package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{

}
