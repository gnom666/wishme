package wishes.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import wishes.model.Attachment;
import wishes.controler.model.Constants.WishStatus;
import wishes.controler.model.Error;

/**
 * Class that represents the wishes of a Person
 * @author jorgerios
 *
 */
@Entity
public class Wish { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public Attachment getAttachment() {
		return attachment;
	}

	public Date getDateFor() {
		return dateFor;
	}		
	
	public String getDescription() {
		return description;
	}

	public Error getError() {
		return error;
	}

	public String getEventFor() {
		return eventFor;
	}

	public long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public Person getLocker() {
		return locker;
	}

	public String getModified() {
		return modified;
	}

	public String getName() {
		return name;
	}

	public Person getOwner() {
		return owner;
	}

	public double getPrice() {
		return price;
	}

	public WishStatus getStatus() {
		return status;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
		updateModified();
	}

	public void setDateFor(Date dateFor) {
		this.dateFor = dateFor;
		updateModified();
	}

	public void setDescription(String description) {
		this.description = description;
		updateModified();
	}

	public void setError(Error error) {
		this.error = error;
		updateModified();
	}

	public void setEventFor(String eventFor) {
		this.eventFor = eventFor;
		updateModified();
	}

	public void setLocation(String location) {
		this.location = location;
		updateModified();
	}

	public void setLocker(Person locker) {
		this.locker = locker;
		updateModified();
	}

	public void setModified(String modified) {
		this.modified = modified;
		updateModified();
	}

	public void setName(String name) {
		this.name = name;
		updateModified();
	}

	public void setOwner(Person owner) {
		this.owner = owner;
		updateModified();
	}

	public void setPrice(double price) {
		this.price = price;
		updateModified();
	}

	public void setStatus(WishStatus status) {
		this.status = status;
		updateModified();
	}

	private void updateModified() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        modified = sdf.format(cal.getTime());
	}
	
}
