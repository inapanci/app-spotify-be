package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	@Query("Select distinct(p.format) from Product p")
	List<String> findAllFormat();


}
