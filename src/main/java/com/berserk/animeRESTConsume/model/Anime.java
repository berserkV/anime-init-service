package com.berserk.animeRESTConsume.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "anime")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Anime {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@ElementCollection
	@CollectionTable(name = "sources", joinColumns = @JoinColumn(name = "id"))
	@Column(name="source")
	private List<String> sources;
	private String type;
	private String title;
	private String picture;
	@ElementCollection
	@CollectionTable(name = "relations", joinColumns = @JoinColumn(name = "id"))
	@Column(name="relation")
	private List<String> relations;
	private String thumbnail;
	private Integer episodes;
	@ElementCollection
	@CollectionTable(name = "synonyms", joinColumns = @JoinColumn(name = "id"))
	@Column(name="synonym")
	private List<String> synonyms;

	public Anime() {
		
	}
	
	public Anime(Long id, ArrayList<String> sources, String type, String title, String picture,
			ArrayList<String> relations, String thumbnail, Integer episodes, ArrayList<String> synonyms) {
		this.id = id;
		this.sources = sources;
		this.type = type;
		this.title = title;
		this.picture = picture;
		this.relations = relations;
		this.thumbnail = thumbnail;
		this.episodes = episodes;
		this.synonyms = synonyms;
	}
	
	public Anime(ArrayList<String> sources, String type, String title, String picture, ArrayList<String> relations,
			String thumbnail, Integer episodes, ArrayList<String> synonyms) {
		this(null, sources, type, title, picture, relations, thumbnail, episodes, synonyms);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<String> getRelations() {
		return relations;
	}

	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Integer episodes) {
		this.episodes = episodes;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
}
