package com.berserk.animeRESTConsume.service;

import java.util.List;

import com.berserk.animeRESTConsume.model.Anime;

public interface AnimeService {
	public boolean processJson(String json);
	public boolean save(List<Anime> myAnimeList);
}