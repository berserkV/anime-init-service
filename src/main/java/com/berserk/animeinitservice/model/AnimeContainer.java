package com.berserk.animeinitservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeContainer {
	@JsonProperty("data")
	List<Anime> animes;

	public AnimeContainer() {

	}

	public AnimeContainer(List<Anime> animes) {
		super();
		this.animes = animes;
	}

	public List<Anime> getAnimes() {
		return animes;
	}

	public void setAnimes(List<Anime> animes) {
		this.animes = animes;
	}	
}