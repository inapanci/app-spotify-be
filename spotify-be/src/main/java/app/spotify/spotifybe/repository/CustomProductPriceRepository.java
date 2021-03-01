package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.CustomProductPrice;

public interface CustomProductPriceRepository extends JpaRepository<CustomProductPrice, Integer>{

}
