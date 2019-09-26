package com.berserk.animeRESTConsume.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.berserk.animeRESTConsume.AnimeRestConsumeApplication;
import com.berserk.animeRESTConsume.service.AnimeService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AnimeRestConsumeApplication.class)
public class ServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private AnimeService animeService;

	@Test
	public void givenJson_whenProcessJson_thenExpectOk() throws Exception {
		final String RESOURCE_LOCATION = "/init/animelist";
		Mockito.when(animeService.processJson(anyString())).thenReturn(true);

		mockMvc
		.perform(
				get(RESOURCE_LOCATION)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

}
