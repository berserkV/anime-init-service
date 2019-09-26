package com.berserk.animeRESTConsume.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.berserk.animeRESTConsume.model.Anime;

@RunWith(MockitoJUnitRunner.class)
public class AnimeServiceTest {

	@Mock
	AnimeService service;

	@Test
	public void whenProcessJson_thenReturnTrue() {
		//given
		when(service.processJson(anyString())).thenReturn(true);

		//when
		boolean result = service.processJson("json");

		//then
		assertTrue(result);
	}
	
	@Test
	public void givenAnimeList_whenSave_thenReturnTrue() {
		//given
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

		//when
		doReturn(true).when(service).save(myAnimeList);

		//then
		assertTrue(service.save(myAnimeList));
		
	}
}
