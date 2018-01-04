package wishes.controler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wishes.config.WishesConfiguration;
import wishes.repository.AttachmentRepository;
import wishes.repository.PersonRepository;
import wishes.repository.WishRepository;

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
	private PersonRepository personRepo;
	
	@Autowired
	private WishesConfiguration eConf;
	
	/**
	 * Gets the attachment of a task
	 * @param taskId The Id of the task
	 * @return List of TaskOut
	 *
	@RequestMapping(value="/attachmentbytask", method=RequestMethod.GET)
    public AttachmentOut attachmentByTask(@RequestParam(value="tId", defaultValue="0") long taskId) {
		eConf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		Task task = taskRepo.findById(taskId);
		if (task == null) {
			return new AttachmentOut(
					new Attachment()
					.setError(eConf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.TASK_NOT_FOUND, "Task not found")));
		}
		
		Attachment attachment = attachRepo.findByTask(task);
		if (attachment == null) {
			return new AttachmentOut(
					new Attachment()
					.setError(eConf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.ATTACHMENT_NOT_FOUND, "Attachment not found or does not exist")));
		}
		
		return new AttachmentOut(attachment);
    }
	
	/**
	 * Gets the attachment by Id
	 * @param attachmentId The Id of the attachment
	 * @return List of TaskOut
	 *
	@RequestMapping(value="/attachmentbyid", method=RequestMethod.GET)
    public AttachmentOut attachmentById(@RequestParam(value="aId", defaultValue="0") long attachmentId) {
		eConf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		Attachment attachment = attachRepo.findById(attachmentId);
		if (attachment == null) {
			return new AttachmentOut(
					new Attachment()
					.setError(eConf.lastError().updateError(ErrorCode.ATTACHMENT_SERVICES, ErrorType.ATTACHMENT_NOT_FOUND, "Attachment not found or does not exist")));
		}
		
		return new AttachmentOut(attachment);
    }*/
	
}
