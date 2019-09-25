package com.berserk.animeRESTConsume.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.model.ListContainer;
import com.berserk.animeRESTConsume.repository.AnimeRepository;
import com.berserk.animeRESTConsume.service.AnimeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("animeService")
public class AnimeServiceImplementation implements AnimeService {
	
	@Autowired
	@Qualifier("animeRepository")
	AnimeRepository animeRepository;
	private static final Logger LOGGER = Logger.getLogger(AnimeServiceImplementation.class);

	@Override
	public boolean processJson(String json) {
		LOGGER.info("Processing json -> " + json);
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ListContainer<Anime> myAnimeContainer = null;
		boolean result = false;
		try {
			myAnimeContainer = objectMapper.readValue(json, new TypeReference<ListContainer<Anime>>() {});
		} catch (Exception e) {
			e.printStackTrace();
			myAnimeContainer = new ListContainer<>(new ArrayList<>());
		}
		List<Anime> myAnimeList = myAnimeContainer.getMyList();
		if (myAnimeList != null && !myAnimeList.isEmpty()) {
			save(myAnimeList);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean save(List<Anime> myAnimeList) {
		return animeRepository.saveAll(myAnimeList) != null ? true :false;
	}
}
