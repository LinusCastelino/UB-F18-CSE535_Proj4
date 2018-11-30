package com.ir.proj4.model;

import java.util.List;

public class Docs {
	String id;
	
    List<String> text;
    List<String> lang;
    List<String> topic;
    List<String> city;
    List<String> userName;
    String verified;
    List<String> imageUrl;
    List<String> tweetDate;
    
    List<String> id_str;
    public List<String> getId_str() {
		return id_str;
	}
	public void setId_str(List<String> id_str) {
		this.id_str = id_str;
	}
	
	public List<String> getUserName() {
		return userName;
	}
	public void setUserName(List<String> userName) {
		this.userName = userName;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public List<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
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
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	 public List<String> getTweetDate() {
			return tweetDate;
	}
	public void setTweetDate(List<String> tweetDate) {
			this.tweetDate = tweetDate;
	}
}
