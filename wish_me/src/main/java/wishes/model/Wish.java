package wishes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wishes.controler.model.Constants.WishStatus;
import wishes.controler.model.Error;

/**
 * Class that represents the wishes of a Person
 * @author jorgerios
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wish { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.PROTECTED)
	private long id;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Person.class)
	@ManyToOne
    @JoinColumn(name="owner_id")
    private Person owner;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Person.class)
	@ManyToOne
    @JoinColumn(name="locker_id")
    private Person locker;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Attachment.class)
	@OneToOne
	@JoinColumn(name="attachment_id")
    private Attachment attachment;
	
	private String name;
	
	private String modified;
	
	private String description;
	
	private double price;
	
	private String location;
	
	private Date dateFor;
	
	private String eventFor;

	@Transient
	private Error error = null;
	
	private WishStatus status;

	public Wish putError(Error error) {
		this.error = error;
		return this;
	}
	
}
