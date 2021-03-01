package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer>{

}
