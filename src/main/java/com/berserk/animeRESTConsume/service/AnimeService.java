package com.berserk.animeRESTConsume.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.model.ListContainer;
import com.berserk.animeRESTConsume.repository.AnimeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("animeService")
public class AnimeService {
	AnimeRepository animeRepository;
	
	private static final Logger LOGGER = Logger.getLogger(AnimeService.class);

	@Autowired
	public AnimeService(@Qualifier("animeRepository") AnimeRepository animeRepository) {
		this.animeRepository = animeRepository;
	}

	public boolean processJson(String animeJson) {
		//=============================
		LOGGER.info("Processing json");
		//=============================
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ListContainer<Anime> myAnimeContainer = null;
		boolean result = false;
		try {
			myAnimeContainer = objectMapper.readValue(animeJson, new TypeReference<ListContainer<Anime>>() {});
		} catch (Exception e) {
			e.printStackTrace();
			myAnimeContainer = new ListContainer<>(new ArrayList<>());
		}
		List<Anime> myAnimes = myAnimeContainer.getMyList();
		if (myAnimes != null && !myAnimes.isEmpty()) {
			result = true;
		} else {
			//==============================================
			LOGGER.error("Anime list may be null or empty");
			//==============================================
			result = false;
		}
		return result;
	}

	public void saveJson(List<Anime> myAnimes) {
		//========================================================
		LOGGER.info("Persisting: " + myAnimes.size() + " animes");
		//========================================================
		myAnimes.stream().map(animeRepository::save);
	}
}