package com.berserk.animeinitservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berserk.animeinitservice.model.Anime;
import com.berserk.animeinitservice.util.Definitions;

@Repository(Definitions.ANIME_REPOSITORY_BEAN)
public interface AnimeRepository extends JpaRepository<Anime, Long> {

}