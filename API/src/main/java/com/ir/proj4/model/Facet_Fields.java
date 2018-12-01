package com.ir.proj4.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Facet_Fields {
	List<String> lang;
	List<String> city;
	List<String> topic;
	
	
	public List<String> getTopic() {
		return topic;
	}

	public void setTopic(List<String> topic) {
		this.topic = topic;
	}

	public List<String> getCity() {
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public List<String> getLang() {
		return lang;
	}

	public void setLang(List<String> lang) {
		this.lang = lang;
	}
}
