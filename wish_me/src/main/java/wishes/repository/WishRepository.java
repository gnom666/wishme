package wishes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import wishes.model.Person;
import wishes.model.Wish;


@RepositoryRestResource(collectionResourceRel = "wishes", path = "wishes")
public interface WishRepository extends PagingAndSortingRepository<Wish, Long> {

	List<Wish> findByName(@Param("name") String name);
	
	List<Wish> findByNameContaining(@Param("name") String name);
	
	Wish findById(@Param("id") long id);
	
	@Transactional
	Wish removeById(@Param("id") long id);
	
	List<Wish> findByOwner(@Param("owner") Person owner);
	
	List<Wish> findByLocker(@Param("locker") Person locker);
}
