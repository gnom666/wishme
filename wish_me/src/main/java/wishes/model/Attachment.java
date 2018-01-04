package wishes.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import wishes.controler.model.Error;

@Entity
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Wish.class)
	@OneToOne(mappedBy="attachment")
    private Wish wish;
	
	private String name;
	
	private String contentB64;
	
	private String modified;
	
	@Transient
	private Error error = null;

	
	
	public Attachment() {}
	
	public Attachment(Wish wish, String name, String contentB64) {
		this.wish = wish;
		this.name = name;
		this.contentB64 = contentB64;
		updateModified();
	}
	
	public Attachment(String name, String contentB64) {
		this.name = name;
		this.contentB64 = contentB64;
		updateModified();
	}

	public String getContentB64() {
		return contentB64;
	}

	public Error getError() {
		return error;
	}

	public long getId() {
		return id;
	}
	
	public String getModified() {
		return modified;
	}

	public String getName() {
		return name;
	}

	public Wish getWish() {
		return wish;
	}

	public void setContentB64(String contentB64) {
		this.contentB64 = contentB64;
		updateModified();
	}

	public Attachment setError(Error error) {
		this.error = error;
		return this;
	}

	public void setModified(String modified) {
		this.modified = modified;
		updateModified();
	}
	
	public void setName(String name) {
		this.name = name;
		updateModified();
	}

	public void setWish(Wish Wish) {
		this.wish = wish;
		updateModified();
	}
	
	private void updateModified() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        modified = sdf.format(cal.getTime());
	}
	
}
