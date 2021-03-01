package app.spotify.spotifybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spotify.spotifybe.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer>{

}
