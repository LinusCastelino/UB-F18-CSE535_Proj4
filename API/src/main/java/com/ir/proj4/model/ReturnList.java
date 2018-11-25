package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnList {
	ArrayList<Docs> tweets;
	HashMap<String,String> lang;
	HashMap<String,String> city;
	int numFound;
	public ReturnList(ArrayList<Docs> tweets,List<String> lang, List<String> city, int numFound){
		this.tweets = tweets;
		this.lang = new HashMap<String,String>();
		this.city = new HashMap<String,String>();
		
		for(int i=0;i<lang.size();i+=2) {
			this.lang.put(lang.get(i), lang.get(i+1));
		}
		for(int i=0;i<city.size();i+=2) {
			this.city.put(city.get(i), city.get(i+1));
		}
		this.numFound = numFound;
	}

	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	public HashMap<String, String> getCity() {
		return city;
	}

	public void setCity(HashMap<String, String> city) {
		this.city = city;
	}

	public ArrayList<Docs> getTweets() {
		return tweets;
	}

	public void setTweets(ArrayList<Docs> tweets) {
		this.tweets = tweets;
	}

	public HashMap<String, String> getLang() {
		return lang;
	}

	public void setLang(HashMap<String, String> lang) {
		this.lang = lang;
	}
}
