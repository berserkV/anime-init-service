package com.berserk.animeRESTConsume.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeRESTConsume.service.AnimeService;

@RestController
@RequestMapping("/init")
public class ServiceController {
	
	@Autowired
	@Qualifier("restTemplate")
	RestTemplate restTemplate;
	@Autowired
	@Qualifier("animeService")
	AnimeService animeService;
	
	private static final Logger LOGGER = Logger.getLogger(ServiceController.class);
	private static final String URL = "https://raw.githubusercontent.com/manami-project/anime-offline-database/master/anime-offline-database.json";
	
	@GetMapping("/animelist")
	public String getAnimeFromAPI() {
		LOGGER.info("Accesing to /api/animelist GET");
		String myAnimeJson = restTemplate.getForObject(URL, String.class);
		boolean success = animeService.processJson(myAnimeJson);
		return String.valueOf(success);
	}
}
