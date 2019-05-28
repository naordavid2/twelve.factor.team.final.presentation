package demo.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {
	
	@Value("${message:Hello world! default value}")
	private String message;
	
	@Value("${errorMessage:ERROR in Hello world! default value}")
	private String errorMessage;
	
	@RequestMapping(path="/hello",
					method=RequestMethod.GET)
	public String sayHello() {
		return this.message;
	}
	
	@RequestMapping(path="/hello/error",
			method=RequestMethod.GET)
	public String sayErorHello() {
		return this.errorMessage;
	}
}
