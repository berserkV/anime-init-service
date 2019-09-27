package com.berserk.animeRESTConsume.service.implementation;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.repository.AnimeRepository;
import com.berserk.animeRESTConsume.service.AnimeService;

@RunWith(SpringRunner.class)
public class AnimeServiceImplementationTest {
	
	private AnimeRepository repository;
	private AnimeService service;
	private List<Anime> myAnimeList;

	@Before
	public void setUp() {
		repository = Mockito.mock(AnimeRepository.class);
		service = new AnimeServiceImplementation(repository);
		myAnimeList = new ArrayList<>(
				Arrays.asList(new Anime(
						1L, 
						null, 
						"TV", 
						"One piece", 
						null, 
						null, 
						null, 
						958, 
						new ArrayList<>())));
	}

	@Test
	public void givenJson_whenProcessJson_thenReturnTrue() {
		//given
		String json = "{\r\n" + 
				"    \"data\": [\r\n" + 
				"        {\r\n" + 
				"            \"sources\": [\r\n" + 
				"                \"https://anidb.net/anime/10143\"\r\n" + 
				"            ],\r\n" + 
				"            \"type\": \"Music\",\r\n" + 
				"            \"title\": \"\\\"0\\\"\",\r\n" + 
				"            \"picture\": \"https://cdn.anidb.net/images/main/143437.jpg\",\r\n" + 
				"            \"relations\": [\r\n" + 
				"                \r\n" + 
				"            ],\r\n" + 
				"            \"thumbnail\": \"https://img7.anidb.net/pics/anime/thumbs/50x65/143437.jpg-thumb.jpg\",\r\n" + 
				"            \"episodes\": 1,\r\n" + 
				"            \"synonyms\": [\r\n" + 
				"                \"Chiaki Kuriyama: \\u300c0\\u300d\",\r\n" + 
				"                \"\\u300c0\\u300d\"\r\n" + 
				"            ]\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		//when
		Mockito.when(repository.save(any(Anime.class))).thenReturn(myAnimeList.get(0));
		//assert
		assertTrue(service.processJson(json));
	}

}