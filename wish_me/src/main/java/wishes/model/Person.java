package wishes.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import wishes.controler.model.Error;

/**
 * Class that represents the Person
 * @author jorgerios
 *
 */
@Entity
public class Person { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
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
	
	public List<Person> getContactOf() {
		return contactOf;
	}

	public List<Person> getContacts() {
		return contacts;
	}

	public String getEmail() {
		return email;
	}

	public Error getError() {
		return error;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Wish> getLocks() {
		return locks;
	}

	public String getModified() {
		return modified;
	}


	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public List<Wish> getWishes() {
		return wishes;
	}

	public boolean isEnabled() {
		return enabled;
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

	public void setContactOf(List<Person> contactOf) {
		this.contactOf = contactOf;
		updateModified();
	}

	public void setContacts(List<Person> contacts) {
		this.contacts = contacts;
		updateModified();
	}

	public void setEmail(String email) {
		this.email = email;
		updateModified();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateModified();
	}

	public Person setError(Error error) {
		this.error = error;
		updateModified();
		return this;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		updateModified();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		updateModified();
	}

	public void setLocks(List<Wish> locks) {
		this.locks = locks;
		updateModified();
	}
	
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public void setPassword(String password) {
		this.password = password;
		updateModified();
	}
	
	public void setRole(Role role) {
		this.role = role;
		updateModified();
	}
	
	public void setWishes(List<Wish> wishes) {
		this.wishes = wishes;
		updateModified();
	}

	private void updateModified() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        modified = sdf.format(cal.getTime());
	}
}
