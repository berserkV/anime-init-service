package com.berserk.animeinitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeinitservice.util.Definitions;

@SpringBootApplication
public class AnimeInitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeInitServiceApplication.class, args);
	}
	
	@Bean(name = Definitions.REST_TEMPLATE_BEAN)
	public RestTemplate rest() {
		return new RestTemplate();
	}
}
