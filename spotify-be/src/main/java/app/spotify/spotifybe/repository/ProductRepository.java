package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
