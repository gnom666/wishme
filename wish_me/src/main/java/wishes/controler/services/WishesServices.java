package wishes.controler.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wishes.config.WishesConfiguration;
import wishes.controler.model.Constants.ErrorCode;
import wishes.controler.model.Constants.ErrorType;
import wishes.model.Person;
import wishes.repository.PersonRepository;
import wishes.repository.WishRepository;
import wishes.view.model.PersonOut;
import wishes.view.model.WishOut;

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
	
	@Autowired
	private WishesConfiguration conf;
	
	/**
	 * Lists all the wishes
	 * @return List of WishOut
	 */
	@RequestMapping(value="/wishlist", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    public List<WishOut> wishList() {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		List<WishOut> all = new ArrayList<>();
		wishRepo.findAll().forEach(w -> all.add(new WishOut(w)));
		return all;
    }
	
	/**
	 * Lists all the wishes of a person
	 * @param pid Person Id
	 * @return List of WishOut
	 */
	@RequestMapping(value="/wishlistbyowner", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    public List<WishOut> wishListByOwner(@RequestParam(value="pid", defaultValue="0") long personId) {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		List<WishOut> all = new ArrayList<>();
		Person owner = personRepo.findById(personId);
		if (owner == null) {
			return new ArrayList<>();
		}
		wishRepo.findByOwner(owner).forEach(w -> all.add(new WishOut(w)));
		return all;
    }
	
	/**
	 * Lists all the wishes locked by a person
	 * @param pid Person Id
	 * @return List of WishOut
	 */
	@RequestMapping(value="/wishlistbylocker", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    public List<WishOut> wishListByLocker(@RequestParam(value="pid", defaultValue="0") long personId) {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		List<WishOut> all = new ArrayList<>();
		Person owner = personRepo.findById(personId);
		if (owner == null) {
			return new ArrayList<>();
		}
		wishRepo.findByLocker(owner).forEach(w -> all.add(new WishOut(w)));
		return all;
    } //*/
}
