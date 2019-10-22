package com.berserk.animeinitservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.berserk.animeinitservice.model.Anime;
import com.berserk.animeinitservice.model.AnimeContainer;
import com.berserk.animeinitservice.repository.AnimeRepository;
import com.berserk.animeinitservice.util.Definitions;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service(Definitions.ANIME_SERVICE_BEAN)
public class AnimeService {
	AnimeRepository animeRepository;
	
	private static final Logger LOGGER = Logger.getLogger(AnimeService.class);

	@Autowired
	public AnimeService(@Qualifier(Definitions.ANIME_REPOSITORY_BEAN) AnimeRepository animeRepository) {
		this.animeRepository = animeRepository;
	}

	public List<Anime> processJson(String animeJson) throws IOException {
		//=============================
		LOGGER.info("Processing json");
		//=============================
		AnimeContainer myAnimeContainer = null;
		myAnimeContainer = readValueFromJson(animeJson);
		List<Anime> myAnimes = myAnimeContainer.getAnimes();
		if (myAnimes == null) { 
			//==============================================
			LOGGER.error("Anime list null");
			//==============================================
			throw new IOException("Cannot deserialize json");
		}
		return myAnimes;
	}

	private AnimeContainer readValueFromJson(String animeJson) {
		AnimeContainer myAnimeContainer;
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			myAnimeContainer = objectMapper.readValue(animeJson, AnimeContainer.class);
		} catch (Exception e) {
			e.printStackTrace();
			myAnimeContainer = new AnimeContainer(new ArrayList<>());
		}
		return myAnimeContainer;
	}

	public void save(List<Anime> myAnimes) {
		//========================================================
		LOGGER.info("Persisting: " + myAnimes.size() + " animes");
		//========================================================
		myAnimes.stream().forEach(animeRepository::save);
	}
	
	public void saveAll(List<Anime> animes) {
		//======================================================
		LOGGER.info("Persisting: " + animes.size() + " animes");
		//======================================================
		animeRepository.saveAll(animes);
	}
}