package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("SELECT count(a) FROM Account a where a.sold is not null and a.sold != 0")
	public Integer getNumberSold();
	
	public List<Account> findByProductId(int productId);

}
