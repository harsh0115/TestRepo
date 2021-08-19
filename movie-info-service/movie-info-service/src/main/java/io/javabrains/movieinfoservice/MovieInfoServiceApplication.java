package io.javabrains.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {

//		return new RestTemplate();
		return new RestTemplateBuilder()
				.setConnectTimeout(Duration.ofMinutes(3))
				.setReadTimeout(Duration.ofMinutes(3))
				.build();
	}

}
