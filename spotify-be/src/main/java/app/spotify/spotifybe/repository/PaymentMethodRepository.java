package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer>{
	
	@Query(value="SELECT * FROM spotify.payment_methods pm where pm.id in (select upm.payment_method_id from spotify.users_payment_methods upm where upm.uuid = ?1);", nativeQuery=true)
	public List<PaymentMethod> getUsersPaymentMethods(String uuid);

}
