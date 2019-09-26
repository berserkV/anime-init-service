package com.berserk.animeRESTConsume.service.implementation;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.repository.AnimeRepository;

public class AnimeServiceImplementationTest {
	
	@Mock
	AnimeRepository repository;
	
	@Before
	public void setUp() {
		List<Anime> myAnimeList = new ArrayList<>(
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
		when(repository.saveAll(myAnimeList))
		.thenReturn(myAnimeList);
	}
	
	@Test
	public void givenJson_whenProcessJson_thenReturnTrue() {
		
	}

}
