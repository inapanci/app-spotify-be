package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
