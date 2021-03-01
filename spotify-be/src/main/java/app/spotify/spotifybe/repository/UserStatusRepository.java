package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.UserStatus;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer>{

}
