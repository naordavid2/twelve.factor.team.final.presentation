package demo.registration.hello.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HelloConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloConsumerApplication.class, args);
	}

}
