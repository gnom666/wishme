package wishes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wishes.controler.model.Error;
import wishes.controler.model.Logger;
import wishes.controler.model.CustomErrorController;
import wishes.controler.model.Constants.*;

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
