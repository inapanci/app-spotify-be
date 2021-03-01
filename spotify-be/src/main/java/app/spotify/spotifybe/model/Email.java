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

	@Column(name="description", length=500)
	private String description;

	public Email() {
	}

	public Email(int id, String description) {
		super();
		this.id = id;
		this.description = description;
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
		return "Email [id=" + id + ", description=" + description + "]";
	}
}