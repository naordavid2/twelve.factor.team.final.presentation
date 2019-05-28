package demo.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoggingController {

	private Logger logger;
	
	@PostConstruct
	public void init() {
		this.logger = LogManager.getLogger(LoggingController.class.getName());
	}
	
	
	@RequestMapping(path = "/hello/{name}",
					method = RequestMethod.GET)
	public String helloWorld(@PathVariable(name="name") String name) throws Exception {
		
		if (name.equals("exception"))
			throw new Exception("**** the value of the path veriable 'name' is 'exception'. Thorowing exception...");
		
		String response = "This is Logging Demo, Hello " + name + ", " + new Date();
		
		this.logger.log(Level.INFO, "helloWorld(String) : " + response);
       
		return response;
	}

	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException (Exception e) {
		String response = "This is an example of logging an Exception " + new Date();
		this.logger.error(response);
		
		this.logger.error(response, e);

		return response;
	}
	
}
