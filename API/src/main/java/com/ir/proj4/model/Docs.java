package com.ir.proj4.model;

import java.util.List;

public class Docs {
	String id;
	
    List<String> text;
    List<String> lang;
    List<String> topic;
    List<String> city;
    List<String> tweet_date;
    List<String> userName;
    List<String> verified;
	List<String> hashtag;
    List<String> imageUrl;
    List<String> tweetUrl;
    List<String> tweetDate;
    public List<String> getUserName() {
		return userName;
	}
	public void setUserName(List<String> userName) {
		this.userName = userName;
	}
	public List<String> getVerified() {
		return verified;
	}
	public void setVerified(List<String> verified) {
		this.verified = verified;
	}
	public List<String> getHashtag() {
		return hashtag;
	}
	public void setHashtag(List<String> hashtag) {
		this.hashtag = hashtag;
	}
	public List<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<String> getTweetUrl() {
		return tweetUrl;
	}
	public void setTweetUrl(List<String> tweetUrl) {
		this.tweetUrl = tweetUrl;
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
	 public List<String> getTweetDate() {
			return tweetDate;
	}
	public void setTweetDate(List<String> tweetDate) {
			this.tweetDate = tweetDate;
	}
}
