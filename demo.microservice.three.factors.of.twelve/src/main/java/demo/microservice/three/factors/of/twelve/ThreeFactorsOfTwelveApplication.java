package demo.microservice.three.factors.of.twelve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThreeFactorsOfTwelveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreeFactorsOfTwelveApplication.class, args);
	}

}
