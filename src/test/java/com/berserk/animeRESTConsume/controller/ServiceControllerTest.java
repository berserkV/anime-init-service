package com.berserk.animeRESTConsume.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.berserk.animeRESTConsume.service.AnimeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ServiceControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ServiceController serviceController;
	
	@Mock
	private AnimeService animeService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serviceController = new ServiceController(animeService, new RestTemplate());
		mockMvc = MockMvcBuilders
                .standaloneSetup(serviceController)
                .build();
		Mockito.reset(animeService);
	}

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
	
	@Test
	public void givenJson_whenProcessJson_thenExpectFalse() throws Exception {
		final String RESOURCE_LOCATION = "/init/animelist";
		Mockito.when(animeService.processJson(anyString())).thenReturn(false);

		mockMvc
		.perform(
				get(RESOURCE_LOCATION)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(content().string("false"));
	}
}