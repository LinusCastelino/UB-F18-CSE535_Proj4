package com.ir.proj4.model;

import java.util.List;

public class Docs {
	String id;
    List<String> text;
//    List<String> entities.urls.url;
    List<String> lang;
    List<String> topic;
    List<String> city;
    List<String> tweet_date;
    float score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	public List<String> getLang() {
		return lang;
	}
	public void setLang(List<String> lang) {
		this.lang = lang;
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
	public List<String> getTweet_date() {
		return tweet_date;
	}
	public void setTweet_date(List<String> tweet_date) {
		this.tweet_date = tweet_date;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
