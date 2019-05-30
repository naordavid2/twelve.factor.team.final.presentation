package demo.registration.hello.producer;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(path="/hello",
			method=RequestMethod.GET)
	public String sayHallo() {
		return "demo.registration.hello.producer says Hello!";
	}
}
