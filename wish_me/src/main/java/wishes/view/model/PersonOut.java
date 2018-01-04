package wishes.view.model;

import java.util.ArrayList;
import java.util.List;

import wishes.controler.model.Error;
import wishes.model.Person;

/**
 * Class to be used as interface of the Person class by the personServices methods
 * @author jorgerios
 *
 */
public class PersonOut {
	
	public long id;
	public List<Long> wishes;
	public List<Long> locks;
	public List<Long> contacts;
	public List<Long> contactOf;
	public String firstName;
	public String lastName;
	public String modified;
	public String email;
	public String password;
	public boolean enabled;
	public int role;
	public Error error;
	
	public PersonOut () {
		wishes = new ArrayList<>();
		locks = new ArrayList<>();
		contacts = new ArrayList<>();
		contactOf = new ArrayList<>();
	}
	
	public PersonOut(Person person) {
		this.wishes = new ArrayList<>();
		this.locks = new ArrayList<>();
		this.contacts = new ArrayList<>();
		this.contactOf = new ArrayList<>();
		
		if (person != null) {
			person.getWishes().forEach(o->this.wishes.add(o.getId()));
			person.getLocks().forEach(a->this.locks.add(a.getId()));
			person.getContacts().forEach(c->this.contacts.add(c.getId()));
			person.getContactOf().forEach(c->this.contactOf.add(c.getId()));
			
			this.id = person.getId();
			this.firstName = person.getFirstName();
			this.lastName = person.getLastName();
			this.modified = person.getModified();
			this.email = person.getEmail();
			this.password = person.getPassword();
			this.enabled = person.isEnabled();
			this.role = person.getRole().getRoleType().ordinal();
			this.error = person.getError();
		}
	}
	
	public PersonOut toPublic() {
		this.password = "";
		this.modified = "";
		return this;
	}
	
}
