package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnList {
	ArrayList<Docs> tweets;
	HashMap<String,String> lang;
	HashMap<String,String> city;
	public ReturnList(ArrayList<Docs> tweets,List<String> lang, List<String> city){
		this.tweets = tweets;
		this.lang = new HashMap<String,String>();
		this.city = new HashMap<String,String>();
		
		for(int i=0;i<lang.size();i+=2) {
			this.lang.put(lang.get(i), lang.get(i+1));
		}
		for(int i=0;i<city.size();i+=2) {
			this.city.put(city.get(i), city.get(i+1));
		}
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
