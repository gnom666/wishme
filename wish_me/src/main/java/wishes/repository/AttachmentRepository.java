package wishes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import wishes.model.Attachment;
import wishes.model.Wish;

@RepositoryRestResource(collectionResourceRel = "attachments", path = "attachments")
public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Long> {

	List<Attachment> findByName(@Param("name") String name);
	
	Attachment findByWish(@Param("wish") Wish wish);
		
	Attachment findById(@Param("id") long id);
	
	@Transactional
	List<Attachment> removeByWish(@Param("wish") Wish wish);
		
}
