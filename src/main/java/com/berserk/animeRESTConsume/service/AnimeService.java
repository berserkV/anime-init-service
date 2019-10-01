package com.berserk.animeRESTConsume.service;

import java.io.IOException;
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

	public List<Anime> processJson(String animeJson) throws IOException {
		//=============================
		LOGGER.info("Processing json");
		//=============================
		ListContainer<Anime> myAnimeContainer = null;
		myAnimeContainer = readValueFromJson(animeJson);
		List<Anime> myAnimes = myAnimeContainer.getMyList();
		if (myAnimes == null || myAnimes.isEmpty()) { 
			//==============================================
			LOGGER.error("Anime list may be null or empty");
			//==============================================
			throw new IOException("Cannot deserialize json");
		}
		return myAnimes;
	}

	private ListContainer<Anime> readValueFromJson(String animeJson) {
		ListContainer<Anime> myAnimeContainer;
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			myAnimeContainer = objectMapper.readValue(animeJson, new TypeReference<ListContainer<Anime>>() {});
		} catch (Exception e) {
			e.printStackTrace();
			myAnimeContainer = new ListContainer<>(new ArrayList<>());
		}
		return myAnimeContainer;
	}

	public void save(List<Anime> myAnimes) {
		//========================================================
		LOGGER.info("Persisting: " + myAnimes.size() + " animes");
		//========================================================
		myAnimes.stream().map(animeRepository::save);
	}
}