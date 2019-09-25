package com.berserk.animeRESTConsume.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.berserk.animeRESTConsume.model.Anime;
import com.berserk.animeRESTConsume.repository.AnimeRepository;
import com.berserk.animeRESTConsume.service.implementation.AnimeServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class AnimeServiceTest {

	@Mock
	AnimeRepository repository;
	@Mock
	AnimeService service = new AnimeServiceImplementation();

//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void whenProcessJson_thenReturnTrue() {
		//given
		when(repository.saveAll(new ArrayList<Anime>()).thenReturn(new ArrayList<Anime>());
		when(service.processJson(anyString())).thenReturn(true);

		//when
		boolean result = service.processJson("json");

		//then
		assertTrue(result);
	}

}
