package wishes.view.model;

import wishes.model.Attachment;
import wishes.controler.model.Error;

/**
 * Class to be used as interface of the Attachment class by the attachmentServices methods
 * @author jorgerios
 *
 */
public class AttachmentOut {

	public long id;
	public String name;
	public String modified;
	public String contentB64;
	public long wish;
	public Error error = null;
	
	public AttachmentOut () {
		
	}

	public AttachmentOut (Attachment attachment) {
		this.id = attachment.getId();
		this.name = attachment.getName();
		this.modified = attachment.getModified();
		this.contentB64 = attachment.getContentB64();
		this.wish = attachment.getWish().getId();
		this.error = attachment.getError();
	}

}
