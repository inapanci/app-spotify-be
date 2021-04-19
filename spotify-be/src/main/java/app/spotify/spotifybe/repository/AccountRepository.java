package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Account;
import app.spotify.spotifybe.model.Product;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("SELECT count(a) FROM Account a where a.sold is not null and a.sold != 0")
	public Integer getNumberSold();
	
	public List<Account> findByProductId(int productId);

	@Query("Select distinct(a.country) From Account a where a.country is not null")
	public List<String> findDistinctCountries();

	@Query("Select distinct(a.subscriptionType) from Account a where a.subscriptionType is not null")
	public List<String> findDistinctSubscriptions();

	@Query(value="SELECT a.* FROM spotify.accounts a where a.product_id in (select o.product_id from spotify.orders o where o.id=?1) limit ?2", nativeQuery=true)
	public List<Account> getAllAccountsOfOrder(long orderId, int quantity);

	public List<Account> findByCountryAndSubscriptionTypeAndProductId(String country, String subscription, Integer productId);

	public List<Account> findByCountryAndProductId(String country, Integer productId);

	public List<Account> findBySubscriptionTypeAndProductId(String subscription, Integer productId);
	
	//find if account was already bought by user
	@Query(value="select count(account_id) from account_buyers where account_id=?1 and buyer_uuid=?2", nativeQuery=true)
	public int findAccountSoldToUser(long accountId, String uuid);

}
