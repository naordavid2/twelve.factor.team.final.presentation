package demo.microservice.three.factors.of.twelve;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class ManagementServiceImpl implements ManagementService {

	private Logger logger;
	private RestTemplate rest;
	
	@Autowired
	EurekaClient discoveryClientEureka;
	
	@PostConstruct
	public void init() {
		this.rest = new RestTemplate();
		this.logger = LogManager.getLogger(ManagementController.class.getName());
	}

	@Override
	public List<Reader> getAllReaders(int size, int page) {
		return Arrays.asList(this.rest.getForObject(getServiceInstanceUrl ("readers-repository", "/readers?size={size}&page={page}"), Reader[].class, size, page));
	}
	
	@Override
	public Reader createReader(String id, String email, String firstName, String lastName) {
		return this.rest.postForObject(getServiceInstanceUrl ("readers-repository","/readers"), new Reader(id, email, firstName, lastName), Reader.class, id);
	}
	
	
	
	
	private String getServiceInstanceUrl(String serviceName, String path) {
		InstanceInfo serviceInstance = discoveryClientEureka.getNextServerFromEureka(serviceName, false);
		String baseURL = serviceInstance.getHomePageUrl();
		logger.log(Level.INFO, "Got instance of service: " + serviceName + ", on url: " + baseURL);
		return baseURL + path;
	}
}
