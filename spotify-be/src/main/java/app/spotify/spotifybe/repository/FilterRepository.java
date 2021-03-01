package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Filter;

public interface FilterRepository extends JpaRepository<Filter,Integer>{

}
