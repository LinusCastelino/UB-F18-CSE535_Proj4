package com.ir.proj4.model;

import java.util.List;

public class Facet_Fields {
	List<String> lang;
	List<String> city;
	List<String> topic;
	List<String> verified;
	List<String> hashtags;
	List<String> tweet_date;

	public List<String> getTweet_date() {
		return tweet_date;
	}

	public void setTweet_date(List<String> tweet_date) {
		this.tweet_date = tweet_date;
	}

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
