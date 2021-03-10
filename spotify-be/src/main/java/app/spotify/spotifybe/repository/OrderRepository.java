package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

	@Query("Select sum(o.value) From Order o")
	public double calculateRevenue();

	@Query("Select count(u) From User u Where u.id in (Select o.user.id from Order o)")
	public int findClients();

}
