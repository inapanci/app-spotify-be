package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Email;

public interface EmailRepository extends JpaRepository<Email,Integer>{

	@Query("Select e from Email e where e.id in (select max(e.id) from Email e)")
	Email findIdMax();

}
