package com.berserk.animeRESTConsume.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
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

//	@Autowired
//	@Qualifier("animeRepository")
	AnimeRepository animeRepository;
	private static final Logger LOGGER = Logger.getLogger(AnimeServiceImplementation.class);
	
	public AnimeServiceImplementation(AnimeRepository animeRepository) {
		this.animeRepository = animeRepository;
	}
	
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
//			animeRepository.saveAll(myAnimeList);
			myAnimeList.stream().map(animeRepository::save);
			result = true;
		} else {
			result = false;
		}
		return result;
	}
}
