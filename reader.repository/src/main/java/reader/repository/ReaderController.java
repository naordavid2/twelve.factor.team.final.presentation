package reader.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderController {
	private ReaderService readers;
	
	@Autowired
	public ReaderController(ReaderService readers) {
		this.readers = readers;
	}
	
	@RequestMapping(
			path="/readers/{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader getReaderById (
			@PathVariable("id") String id) {
		return this.readers
				.getReaderById(id);
	}
	
	@RequestMapping(
			path="/readers",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader[] getAllReaders (
			@RequestParam(name="size", required=false, defaultValue="5") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return 
			this.readers
				.getAllReaders(size, page)
				.toArray(new Reader[0]);
	}
	
	@RequestMapping(
			path="/readers",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public Reader writeReader (
			@RequestBody Reader reader) {
		return this.readers
				.writeReader(reader);
	}
	
	@RequestMapping(
			path="/readers",
			method=RequestMethod.DELETE)
	public void deleteAll() {
		this.readers
			.deleteAll();
		
	}

	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Map<String, Object> handleError (ReaderNotFoundException e){
		Map<String, Object> rv = new HashMap<>();
		rv.put("error", "Reader could not be found");
		return rv;
	}
	
}












