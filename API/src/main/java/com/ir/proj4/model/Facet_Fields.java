package com.ir.proj4.model;

import java.util.List;



public class Facet_Fields {
	List<String> lang;
	List<String> city;
	List<String> topic;
	List<String> verified;
	List<String> hashtags;
	
	public List<String> getVerified() {
		return verified;
	}

	public void setVerified(List<String> verified) {
		this.verified = verified;
	}

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
	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

}
