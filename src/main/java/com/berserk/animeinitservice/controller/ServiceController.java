package com.berserk.animeinitservice.controller;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeinitservice.model.Anime;
import com.berserk.animeinitservice.service.AnimeService;
import com.berserk.animeinitservice.util.Definitions;
import com.berserk.animeinitservice.util.URL;

@RestController
@RequestMapping(URL.INIT_BLOCK)
public class ServiceController {
	private final AnimeService animeService;
	private final RestTemplate restTemplate;
	
	private static final Logger LOGGER = Logger.getLogger(ServiceController.class);
	private static final String URI_RESOURCE =
			"https://raw.githubusercontent.com/manami-project/anime-offline-database/master/"
			+"anime-offline-database.json";
	
	@Autowired
	public ServiceController(@Qualifier(Definitions.ANIME_SERVICE_BEAN) AnimeService animeService,
			@Qualifier(Definitions.REST_TEMPLATE_BEAN) RestTemplate restTemplate) {
		this.animeService = animeService;
		this.restTemplate = restTemplate;
	}

	@GetMapping(URL.SAVE_BLOCK)
	public List<Anime> getAnimeFromAPI() throws IOException {
		LOGGER.info("Accesing to /api/animelist GET");
		String myAnimeJson = restTemplate.getForObject(URI_RESOURCE, String.class);
		List<Anime> myAnimes = animeService.processJson(myAnimeJson);
		animeService.save(myAnimes);
		return myAnimes;
	}
	
	@GetMapping(URL.SAVE_ALL_BLOCK)
	public List<Anime> saveAnimes() throws IOException {
		LOGGER.info("Accesing to /api/save GET");
		String myAnimeJson = restTemplate.getForObject(URI_RESOURCE, String.class);
		List<Anime> myAnimes = animeService.processJson(myAnimeJson);
		animeService.saveAll(myAnimes);
		return myAnimes;
	}
}
