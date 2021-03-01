package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Email;

public interface EmailRepository extends JpaRepository<Email,Integer>{

}
