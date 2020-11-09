package ro.telacad.db;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event implements Comparable<Event> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Embedded
	private Adress adress;

	@OneToMany(mappedBy = "event", orphanRemoval = true)
	private Set<Guest> guests = new HashSet<>();

	public Event() {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Set<Guest> getGuests() {
		return guests;
	}

	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Event))
			return false;
		Event event = (Event) o;
		return id == event.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Event: " + name + ", Date: " + date + ", " + adress;
	}

	@Override
	public int compareTo(Event o) {
		return this.getDate().compareTo(o.getDate());
	}
}