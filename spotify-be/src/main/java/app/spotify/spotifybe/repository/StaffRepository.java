package app.spotify.spotifybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.spotify.spotifybe.model.Staff;

public interface StaffRepository extends JpaRepository<Staff,Integer>{

	@Query("Select s From Staff s where s.online = '1'")
	public List<Staff> findOnlineStaff();

}
