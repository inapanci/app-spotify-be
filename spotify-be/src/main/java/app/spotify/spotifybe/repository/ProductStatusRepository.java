package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.ProductStatus;

public interface ProductStatusRepository extends JpaRepository<ProductStatus,Integer>{

}
