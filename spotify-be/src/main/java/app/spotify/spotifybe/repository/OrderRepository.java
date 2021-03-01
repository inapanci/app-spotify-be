package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
