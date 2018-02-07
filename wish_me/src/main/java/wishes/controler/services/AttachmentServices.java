package wishes.controler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wishes.config.WishesConfiguration;
import wishes.controler.model.Constants.ErrorCode;
import wishes.controler.model.Constants.ErrorType;
import wishes.model.Attachment;
import wishes.model.Wish;
import wishes.repository.AttachmentRepository;
import wishes.repository.PersonRepository;
import wishes.repository.RoleRepository;
import wishes.repository.WishRepository;
import wishes.view.model.AttachmentOut;

/**
 * Attachments Services
 * @author jorgerios
 *
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentServices {
	
	@Autowired
	private WishRepository wishRepo;
	
	@Autowired
	private AttachmentRepository attachRepo;

	@Autowired
	private WishesConfiguration conf;
	
	/**
	 * Gets the attachment of a wish
	 * @param taskId The Id of the task
	 * @return List of {@link AttachmentOut}
	 */
	@RequestMapping(value="/attachmentbywish", method=RequestMethod.GET)
    public AttachmentOut attachmentByWish(@RequestParam(value="wid", defaultValue="0") long wishId) {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		Wish wish = wishRepo.findById(wishId);
		if (wish == null) {
			return new AttachmentOut(
					new Attachment()
					.putError(conf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.WISH_NOT_FOUND, "Wish not found")));
		}
		
		Attachment attachment = attachRepo.findByWish(wish);
		if (attachment == null) {
			return new AttachmentOut(
					new Attachment()
					.putError(conf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.ATTACHMENT_NOT_FOUND, "Attachment not found or does not exist")));
		}
		
		return new AttachmentOut(attachment);
    }
	
	/**
	 * Gets the attachment by Id
	 * @param attachmentId The Id of the attachment
	 * @return List of {@link AttachmentOut}
	 */
	@RequestMapping(value="/attachmentbyid", method=RequestMethod.GET)
    public AttachmentOut attachmentById(@RequestParam(value="aid", defaultValue="0") long attachmentId) {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		Attachment attachment = attachRepo.findById(attachmentId);
		if (attachment == null) {
			return new AttachmentOut(
					new Attachment()
					.putError(conf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.ATTACHMENT_NOT_FOUND, "Attachment not found or does not exist")));
		}
		
		return new AttachmentOut(attachment);
    }
	
}
