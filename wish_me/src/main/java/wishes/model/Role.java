package wishes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wishes.controler.model.Constants.RoleType;


/**
 * Class that represents the different roles of a Person
 * @author jorgerios
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	@Setter(AccessLevel.PROTECTED)
	private long id;
	
	@Column(nullable = false, updatable = false, unique = true)
	private RoleType roleType;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Person.class)
	@OneToMany(mappedBy = "role")
	private List<Person> members;

	@Transient
	private Error error = null;
	
	public Role putError(Error error) {
		this.error = error;
		return this;
	}

}
