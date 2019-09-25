package com.berserk.animeRESTConsume.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListContainer <T> {
	@JsonProperty("data")
	List<T> myList;
	
	public ListContainer() {
		
	}
	
	public ListContainer(List<T> myList) {
		super();
		this.myList = myList;
	}
	
	public List<T> getMyList() {
		return myList;
	}

	public void setMyList(List<T> myList) {
		this.myList = myList;
	}
}
