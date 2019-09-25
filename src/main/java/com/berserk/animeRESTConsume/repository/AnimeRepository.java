package com.berserk.animeRESTConsume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berserk.animeRESTConsume.model.Anime;

@Repository("animeRepository")
public interface AnimeRepository extends JpaRepository<Anime, Long> {

}