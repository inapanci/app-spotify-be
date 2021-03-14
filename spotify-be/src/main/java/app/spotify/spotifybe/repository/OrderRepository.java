package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

	@Query("Select sum(o.value) From Order o")
	public double calculateRevenue();

	@Query("Select count(u) From User u Where u.id in (Select o.user.id from Order o)")
	public int findClients();

	public List<Order> findByOrderStatusDescription(String descr);

	@Query("Select sum(o.quantity) From Order o Where o.user.id=?1")
	public int getAccountsPurchased(String uuid);

	@Query("Select sum(o.value) From Order o Where o.user.id=?1")
	public double getAmountSpent(String uuid);

	public List<Order> findByUserId(String uuid);

	public List<Order> findByUserIdAndOrderStatusDescription(String uuid, String descr);

}
