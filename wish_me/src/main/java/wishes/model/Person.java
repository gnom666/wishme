package wishes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wishes.controler.model.Error;

/**
 * Class that represents the Person
 * @author jorgerios
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	@Setter(AccessLevel.PROTECTED)
	private long id;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Wish.class)
	@OneToMany(mappedBy = "owner")
	private List<Wish> wishes;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Wish.class)
	@OneToMany(mappedBy = "locker")
	private List<Wish> locks;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Person.class)
	@ManyToMany(cascade = CascadeType.ALL,
				targetEntity = Person.class,
				fetch = FetchType.LAZY)
	@JoinTable(name = "person_person",
			   joinColumns = @JoinColumn(name = "contactOf", referencedColumnName = "id"), 
			   inverseJoinColumns = @JoinColumn(name = "contact", referencedColumnName = "id"))
	private List<Person> contacts;
	
	@ManyToMany(cascade = CascadeType.ALL,
				targetEntity = Person.class,
				fetch = FetchType.LAZY)
	@JoinTable(	name = "person_person",
				joinColumns = @JoinColumn(name = "contact", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "contactOf", referencedColumnName = "id"))
	private List<Person> contactOf;
	
	private String firstName;

	private String lastName;

	private String modified;

	@Column(unique = true, nullable = false, length = 45)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Role.class)
	@ManyToOne
    @JoinColumn(name="role")
    private Role role;
	
	@Transient
	private Error error = null;
	
	
	
	public List<Person> addToContactOf (Person person) {
		for (Person p : contactOf) {
			if (p.id == person.id) return contactOf;
		}
		contactOf.add(person);
		return contactOf;
	}
	
	public List<Person> addToContacts (Person person) {
		for (Person p : contacts) {
			if (p.id == person.id) return contacts;
		}
		contacts.add(person);
		return contacts;
	}
	
	public List<Person> removeFromContactOf (Person person) {
		for (Person p : contactOf) {
			if (p.id == person.id) {
				contactOf.remove(p);
				return contactOf;
			}
		}
		return contactOf;
	}

	public List<Person> removeFromContacts (Person person) {
		for (Person p : contacts) {
			if (p.id == person.id) {
				contacts.remove(p);
				return contacts;
			}
		}
		return contacts;
	}
	
	public Person putError (Error error) {
		this.error = error;
		return this;
	}
}
