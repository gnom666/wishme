package wishes.view.model;

import java.util.Date;

import wishes.controler.model.Error;
import wishes.model.Wish;

/**
 * Class to be used as interface of the Wish class by the wishServices methods
 * @author jorgerios
 *
 */
public class WishOut {
	
	public long id;
    public long owner;
    public long locker;
    public long attachment;
	public String name;
	public String modified;
	public String description;
	public double price;
	public String location;
	public Date dateFor;
	public String eventFor;
	public Error error = null;
	
	public WishOut () {
		
	}
	
	public WishOut(Wish wish) {
		this.id = wish.getId();
		this.owner = (wish.getOwner() != null)? wish.getOwner().getId(): 0;
		this.locker = (wish.getLocker() != null)? wish.getLocker().getId(): 0;
		this.attachment = (wish.getAttachment() != null)? wish.getAttachment().getId(): 0;
		this.name = wish.getName();
		this.modified = wish.getModified();
		this.description = wish.getDescription();
		this.price = wish.getPrice();
		this.location = wish.getLocation();
		this.dateFor = wish.getDateFor();
		this.eventFor = wish.getEventFor();
		this.error = wish.getError();
	}	
	
}
