package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
