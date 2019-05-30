package demo.microservice.three.factors.of.twelve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {
	private Logger logger;
	private ManagementService managementService;

	@Autowired	
	public ManagementController(ManagementService managementService) {
		super();
		this.managementService = managementService;
		this.logger = LogManager.getLogger(ManagementController.class.getName());
	}
	
	
	@RequestMapping(path = "/hello",
					method = RequestMethod.GET)
	public String sayHello() {
		String response = "This Demo says Hello on - " + new Date();
		
		this.logger.log(Level.INFO, "sayHello() : " + response);
       
		return response;
	}
	
	@RequestMapping(path = "/hello/readers",
			method = RequestMethod.GET)
	public String[] sayHelloToReaders(
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		List<Reader> readers = this.managementService.getAllReaders(size, page);
		List<String> hellos = new ArrayList<>();
		readers.forEach(r->hellos.add("Hello " + r.getFirstName() + "!!!"));				
		return hellos.toArray(new String[0]);
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			path="/readers/{id}/{email}/{firstName}/{lastName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader createReader(
			@PathVariable("id") String id,
			@PathVariable("email") String email,
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName){
		Reader newReader = this.managementService.createReader(id,email,firstName,lastName); 
		this.logger.log(Level.INFO, "New Reader was created: " + newReader.toString());
		return newReader;
	}
}
