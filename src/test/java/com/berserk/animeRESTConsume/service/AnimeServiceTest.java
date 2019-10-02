package com.berserk.animeRESTConsume.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.model.ListContainer;
import com.berserk.animeRESTConsume.repository.AnimeRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AnimeService.class)
public class AnimeServiceTest {
	
	@Mock
	private AnimeRepository repository;
	
	private AnimeService service;
	private String json;

	@Before
	public void setUp() {
		repository = Mockito.mock(AnimeRepository.class);
		service = PowerMockito.spy(new AnimeService(repository));
		json = "{\r\n" + 
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
	}
	
	@Test
	public void givenJson_whenProcessJson_thenReturnAnimeList() throws Exception {
		PowerMockito.when(service, "readValueFromJson", json).thenReturn(
				new ListContainer<>(new ArrayList<>(
						Arrays.asList(new Anime(
								1L, 
								null, 
								"TV", 
								"One piece", 
								null, 
								null, 
								null, 
								958, 
								new ArrayList<>())))));
		
		assertEquals("One piece", service.processJson(json).get(0).getTitle());
	}
	
	@Test
	public void givenJson_whenProcessJson_thenReturnEmptyList() throws Exception {
		PowerMockito.when(service, "readValueFromJson", json)
				.thenReturn(new ListContainer<>(new ArrayList<>()));
		
		List<Anime> myActuals = service.processJson(json);
		
		assertTrue(myActuals.isEmpty());
	}
	
	@Test
	public void givenAnimeList_whenSave_verifyIfListWasPersisted() {
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
		
		when(repository.save(ArgumentMatchers.any(Anime.class))).thenReturn(myAnimes.get(0));
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(myAnimes.get(0)));
		
		service.save(myAnimes);
		assertEquals("One piece", repository.findById(1L).get().getTitle());
	}
}
