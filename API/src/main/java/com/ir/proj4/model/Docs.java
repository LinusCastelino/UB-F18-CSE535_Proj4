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
    List<String> userProfile;
    List<String> tweet_date;
    String semanticScore = "0";
    
    
    public String getSemanticScore() {
		return semanticScore;
	}
	public void setSemanticScore(String semanticScore) {
		this.semanticScore = semanticScore;
	}
	
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
	public List<String> getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(List<String> userProfile) {
		this.userProfile = userProfile;
	}
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
}
