package wishes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wishes.controler.model.Error;
import wishes.controler.Logger;
import wishes.controler.CustomErrorController;
import wishes.controler.model.Constants.*;
import wishes.repository.AttachmentRepository;
import wishes.repository.PersonRepository;
import wishes.repository.RoleRepository;
import wishes.repository.WishRepository;

@Configuration
public class WishesConfiguration {
	
	@Autowired
	@Bean
	public Error lastError() {
		return new Error(ErrorCode.UNKNOWN, ErrorType.UNKNOWN, "");
	}
	
	@Autowired
	private ErrorAttributes errorAttributes;

	@Bean
	public CustomErrorController customErrorController(){return new CustomErrorController(errorAttributes);}
	
	@Bean
	public Logger logger() { return new Logger();}
	
}
