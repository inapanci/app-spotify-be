package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the emails database table.
 * 
 */
@Entity
@Table(name="emails")
@NamedQuery(name="Email.findAll", query="SELECT e FROM Email e")
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="email", length=500)
	private String email;

	public Email() {
	}

	public Email(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", email=" + email + "]";
	}
}