package demo.registration.hello.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ConsumerController {

	@Autowired
	DiscoveryClient discoveryClient;
	
	@Autowired
	EurekaClient discoveryClientEureka;
	
	@RequestMapping(path="/consume",
			method=RequestMethod.GET)
	public String consume() {
		InstanceInfo serviceInstance = discoveryClientEureka.getNextServerFromEureka("demo-registration-hello-producer", false);
		String baseURL = serviceInstance.getHomePageUrl();
		RestTemplate restTemplate = new RestTemplate();
		
		String responseString = "";
		
		try{
			ResponseEntity<String> response=restTemplate.getForEntity(baseURL + "/hello", String.class);
			if (response.getStatusCode().is2xxSuccessful())
				responseString = response.getBody();
			else
				throw new HttpClientErrorException (HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
	
		return "The producer on address: " + baseURL + " produced the message: " + responseString;
	}
	
	@RequestMapping(path="/services",
			method=RequestMethod.GET)
	public String[] getServices() {
		return discoveryClient.getServices().toArray(new String[0]);
	}
}
