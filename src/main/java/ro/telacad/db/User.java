package ro.telacad.db;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@NotNull(message = "Username can't be null")
	@Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
	private String username;

	@NotNull(message = "Password can't be null")
	@Size(min = 4, max = 10, message = "Password must be between 4 and 10 character")
	private String password;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return id == user.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
