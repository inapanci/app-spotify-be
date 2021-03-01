package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.User;

public interface UserRepository extends JpaRepository<User,String>{

}
