package com.berserk.animeRESTConsume.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
}
