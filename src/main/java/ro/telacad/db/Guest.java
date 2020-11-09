package ro.telacad.db;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "guests")
public class Guest implements Comparable<Guest> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private String name;

	private String email;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "guests_phoneNumbers")
	private Set<String> phoneNumbers = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	public Guest() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Guest))
			return false;
		Guest guest = (Guest) o;
		return id == guest.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Email: " + email + ", Phone: " + phoneNumbers;
	}

	@Override
	public int compareTo(Guest o) {
		return this.name.compareTo(o.name);
	}

}
