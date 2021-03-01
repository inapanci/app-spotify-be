package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.TransactionStatus;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,Integer>{

}
