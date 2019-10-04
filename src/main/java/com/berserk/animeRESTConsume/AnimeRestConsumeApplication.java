package com.berserk.animeRESTConsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AnimeRestConsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeRestConsumeApplication.class, args);
	}
	
	@Bean(name = "restTemplate")
	public RestTemplate rest() {
		return new RestTemplate();
	}
}
