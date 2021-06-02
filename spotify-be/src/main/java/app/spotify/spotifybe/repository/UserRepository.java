package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.spotify.spotifybe.model.PaymentMethod;
import app.spotify.spotifybe.model.User;

public interface UserRepository extends JpaRepository<User,String>{

	@Query("Select sum(u.balance) from User u")
	public Double getAllBalance();

	@Query("Select count(distinct s.user.id) from Staff s where s.online='1'")
	public Integer getOnlineStaff();

	public User findByEmail(String email);
	
	@Query(value = "SELECT * FROM spotify.users u WHERE u.email=?1 AND u.password=?2", 
			  nativeQuery = true)
	public User findByEmailAndPassword( String email, String password);

}
