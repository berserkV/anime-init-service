package com.berserk.animeRESTConsume.service;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.repository.AnimeRepository;

@RunWith(MockitoJUnitRunner.class)
public class AnimeServiceTest {
	
	@Mock
	private AnimeRepository repository;
	
	private AnimeService service;

	@Before
	public void setUp() {
		repository = Mockito.mock(AnimeRepository.class);
		service = new AnimeService(repository);
	}
	
	@Test
	public void givenJson_whenGetJson_thenReturnTrue() {
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
		
		
		assertTrue(service.processJson(json));
	}
	
	@Test
	public void givenBadFormatJson_whenGetJson_thenReturnFalse() {
		String animeJson = "{\r\n" + 
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
				"    ]\r\n";
		
		assertFalse(service.processJson(animeJson));
	}
	
	@Test
	public void givenAnimeList_verifySave() {
		List<Anime> myAnimes = new ArrayList<>(
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
		
		when(repository.save(any(Anime.class))).thenReturn(myAnimes.get(0));
		
		service.saveJson(myAnimes);
	}
}
