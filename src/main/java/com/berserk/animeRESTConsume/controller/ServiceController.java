package com.berserk.animeRESTConsume.controller;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.service.AnimeService;

@RestController
@RequestMapping("/init")
public class ServiceController {
	AnimeService animeService;
	RestTemplate restTemplate;
	
	private static final Logger LOGGER = Logger.getLogger(ServiceController.class);
	private static final String URL =
			"https://raw.githubusercontent.com/manami-project/anime-offline-database/master/"
			+"anime-offline-database.json";
	
	@Autowired
	public ServiceController(@Qualifier("animeService") AnimeService animeService,
			@Qualifier("restTemplate") RestTemplate restTemplate) {
		this.animeService = animeService;
		this.restTemplate = restTemplate;
	}

	@GetMapping("/animelist")
	public List<Anime> getAnimeFromAPI() throws IOException {
		LOGGER.info("Accesing to /api/animelist GET");
		String myAnimeJson = restTemplate.getForObject(URL, String.class);
		List<Anime> myAnimes = animeService.processJson(myAnimeJson);
		animeService.save(myAnimes);
		return myAnimes;
	}
}
