package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_status database table.
 * 
 */
@Entity
@Table(name="user_status")
@NamedQuery(name="UserStatus.findAll", query="SELECT u FROM UserStatus u")
public class UserStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

//	//bi-directional many-to-one association to User
//	@OneToMany(mappedBy="userStatus")
//	private List<User> users;

	public UserStatus() {
	}

	public UserStatus(int id, String description) {
		super();
		this.id = id;
		this.description = description;
		//this.users = users;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserStatus [id=" + id + ", description=" + description + "]";
	}

}
