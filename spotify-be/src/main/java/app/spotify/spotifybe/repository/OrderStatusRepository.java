package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer>{

	OrderStatus findByDescription(String descr);

}
