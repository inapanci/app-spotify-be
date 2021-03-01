package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.UserPromotion;

public interface UserPromotionRepository extends JpaRepository<UserPromotion,Integer>{

}
