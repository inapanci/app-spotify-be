package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	List<Transaction> findByUserId(String uuid);

}
