package wishes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wishes.controler.model.Error;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

	
	public Attachment(Wish wish, String name, String contentB64) {
		this.wish = wish;
		this.name = name;
		this.contentB64 = contentB64;
	}
	
	public Attachment(String name, String contentB64) {
		this.name = name;
		this.contentB64 = contentB64;
	}

	public Attachment putError(Error error) {
		this.error = error;
		return this;
	}
	
}
