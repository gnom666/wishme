package wishes.controler.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wishes.controler.model.Constants;
import wishes.config.WishesConfiguration;
import wishes.controler.model.Constants.RoleType;
import wishes.model.Attachment;
import wishes.model.Person;
import wishes.model.Role;
import wishes.model.Wish;
import wishes.repository.AttachmentRepository;
import wishes.repository.PersonRepository;
import wishes.repository.RoleRepository;
import wishes.repository.WishRepository;

/**
 * General Services
 * @author jorgerios
 *
 */
@RestController
@RequestMapping("/general")
public class GeneralServices {
	
	@Autowired
	private WishRepository wishRepo;
	
	@Autowired
	private AttachmentRepository attachRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private WishesConfiguration conf;
	
	/**
	 * Fills the database with some coherent data to do tests 
	 * @return Log String
	 */
	@RequestMapping(value="/init", method=RequestMethod.GET)
    public String init() {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		StringBuffer result = new StringBuffer();
    	
		Role roleAdm = new Role();
    	Role roleAUser = new Role();
    	Role roleCUser = new Role();
    	Role roleGuest = new Role();
    	
    	Person personA = new Person();
    	Person personB = new Person();
    	Person personC = new Person();
    	Person personD = new Person();
    	Person personE = new Person();
    	
    	Wish wishA = new Wish();
    	Wish wishB = new Wish();
    	Wish wishC = new Wish();
    	Wish wishD = new Wish();
    	Wish wishE = new Wish();
    	
    	Attachment attachment = null;
    	
    	//* Alta de roles de prueba
    	try {
    		result.append("Alta de roles de prueba...");
    		
	    	roleAdm.setRoleType(RoleType.ADMINISTRATOR);
	    	roleAUser.setRoleType(RoleType.ADVANCED_USER);
	    	roleCUser.setRoleType(RoleType.COMMON_USER);
	    	roleGuest.setRoleType(RoleType.GUEST);
	    	
	    	roleRepo.save(roleAdm);
	    	roleRepo.save(roleAUser);
	    	roleRepo.save(roleCUser);
	    	roleRepo.save(roleGuest);
	    	
    	}	catch (Exception e) {
    		result.append("ERROR\n" + e.getMessage());
    		return result.toString();
    	}
    	result.append("OK\n");
    	//*/
    	
    	//* Alta de personas de prueba
    	try {
    		result.append("Alta de personas de prueba...");
    		
    		personA.setFirstName("A.");
	    	personA.setLastName("Alce");
	    	personA.setPassword("apass");
	    	personA.setEmail("a@eq.com");
	    	personA.setRole(roleAdm);
	    	personA.setEnabled(true);
	    	
	    	personB.setFirstName("B.");
	    	personB.setLastName("Beluga");
	    	personB.setPassword("bpass");
	    	personB.setEmail("b@eq.com");
	    	personB.setRole(roleCUser);
	    	personB.setEnabled(true);
	    	
	    	personC.setFirstName("C.");
	    	personC.setLastName("Castor");
	    	personC.setPassword("cpass");
	    	personC.setEmail("c@eq.com");
	    	personC.setRole(roleCUser);
	    	personC.setEnabled(true);
	    	
	    	personD.setFirstName("D.");
	    	personD.setLastName("Dodo");
	    	personD.setPassword("dpass");
	    	personD.setEmail("d@eq.com");
	    	personD.setRole(roleCUser);
	    	personD.setEnabled(true);
	    	
	    	personE.setFirstName("E.");
	    	personE.setLastName("Erizo");
	    	personE.setPassword("epass");
	    	personE.setEmail("e@eq.com");
	    	personE.setRole(roleGuest);
	    	personE.setEnabled(true);
	    	
	    	personRepo.save(personA);
	    	personRepo.save(personB);
	    	personRepo.save(personC);
	    	personRepo.save(personD);
	    	personRepo.save(personE);
	    	
	    	ArrayList<Person> contacts = new ArrayList<>();
	    	contacts.add(personB);
	    	contacts.add(personC);
	    	contacts.add(personD);
	    	contacts.add(personE);
	    	personA.setContacts(contacts);
	    	
	    	contacts = new ArrayList<>();
	    	contacts.add(personA);
	    	contacts.add(personC);
	    	contacts.add(personD);
	    	personB.setContacts(contacts);
	    	
	    	contacts = new ArrayList<>();
	    	contacts.add(personA);
	    	contacts.add(personB);
	    	contacts.add(personD);
	    	personC.setContacts(contacts);
	    	
	    	contacts = new ArrayList<>();
	    	contacts.add(personB);
	    	contacts.add(personC);
	    	contacts.add(personA);
	    	personD.setContacts(contacts);
	    	
	    	contacts = new ArrayList<>();
	    	contacts.add(personA);
	    	personE.setContacts(contacts);
	    	
	    	personRepo.save(personA);
	    	personRepo.save(personB);
	    	personRepo.save(personC);
	    	personRepo.save(personD);
	    	personRepo.save(personE);
	    	
    	}	catch (Exception e) {
    		result.append("ERROR\n" + e.getMessage());
    		return result.toString();
    	}
    	result.append("OK\n");
    	//*/
    	
    	//* Alta de deseos de prueba
    	try {
    		result.append("Alta de deseos de prueba...");
    		
	    	wishA.setName("Dron");
	    	wishA.setDescription("Parrot en amazon");
	    	wishA.setDateFor(new Date());
	    	wishA.setEventFor("Reyes");
	    	wishA.setLocation("http://www.amazon.es");
	    	wishA.setOwner(personA);
	    	wishA.setLocker(personE);
	    	wishA.setStatus(Constants.WishStatus.LOCKED);
	    	wishA.setPrice(100.00);
	    	
	    	wishB.setName("Libro");
	    	wishB.setDescription("La ultima cupada del Mango");
	    	wishB.setDateFor(new Date());
	    	wishB.setEventFor("14 Febrero");
	    	wishB.setLocation("http://www.aliexpress.com");
	    	wishB.setOwner(personE);
	    	wishB.setLocker(null);
	    	wishB.setStatus(Constants.WishStatus.FREE);
	    	wishB.setPrice(999.99);
	    	
	    	wishRepo.save(wishA);
	    	wishRepo.save(wishB);
    	
    	}	catch (Exception e) {
    		result.append("ERROR\n" + e.getMessage());
    		return result.toString();
    	}
    	result.append("OK\n");
    	//*/
    	
    	return result.toString();
    }
	
	private int commute (int x) {
		return x + x * (-2) + 1;
	}  
	
	private String bufferToStr (List<Character> buffer) {
		StringBuilder sb = new StringBuilder();
		buffer.forEach(c -> sb.append(c));
		
		return sb.toString();
	}
	
	/**
	 * Returns the tail of the out.log file
	 * @return Log String
	 */
	@RequestMapping(value="/logtail", method=RequestMethod.GET)
    public String getLogTail(@RequestParam(value="log", defaultValue="wout.log") String logName,
    		@RequestParam(value="size", defaultValue="100") int bufferSize) {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		String result = "";
		
		int size = bufferSize * 1024;
		if (bufferSize % 2 != 0) size++;
		int limit = size / 2;
		ArrayList<Character> buffer = new ArrayList<>(size);
		for (int j = 0; j < size; j++) {
			buffer.add(' ');
		}
		
		int switcher = 0;
		int i = 0;
		
		File file = new File(logName);
	    if (!file.exists()) {
	    	return (logName + " does not exist.");
	    }
	    if (!(file.isFile() && file.canRead())) {
	    	return (logName + " cannot be read from.");
	    }
	    try {
		    FileInputStream fis = new FileInputStream(file);
		    char current;
		    while (fis.available() > 0) {
		    	current = (char) fis.read();
		    	int pos = switcher * limit + i;
		    	buffer.set(pos, current);
		    	
		    	i++;
		    	if (i == limit) {
		    		i = 0;
		    		switcher = commute(switcher);
		    	}
		    }
		    
		    fis.close();
		    
	    } 	catch (IOException e) {
	    	return e.getMessage();
	    }
		
	    int commuted = commute(switcher);
	    result = bufferToStr(buffer.subList(commuted * limit + i, commuted * limit + limit)) +
	    		bufferToStr(buffer.subList(switcher * limit, switcher * limit + i));
		
	    return result;
	}
	
	/**
	 * Connection Test
	 * @return String PONG
	 */
	@RequestMapping(value="/ping", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
    public String ping() {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		return "PONG";
    }
	
	/**
	 * Returns the timestamp
	 * @return
	 */
	@RequestMapping(value="/timestamp", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	public String timestamp () {
		conf.logger().log(this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName());
		String result = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		return result;
    }
	
}
