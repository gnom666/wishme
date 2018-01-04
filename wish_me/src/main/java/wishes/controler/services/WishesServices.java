package wishes.controler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wishes.repository.PersonRepository;
import wishes.repository.WishRepository;

/**
 * Wish Services
 * @author jorgerios
 *
 */
@RestController
@RequestMapping("/wishes")
public class WishesServices {
	
	@Autowired
	private WishRepository wishRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	
}
