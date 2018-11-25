package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnList {
	ArrayList<Docs> tweets;
	HashMap<String,String> lang;
	
	public ReturnList(ArrayList<Docs> arrayList,List<String> lang){
		this.tweets = arrayList;
		this.lang = new HashMap<String,String>();
		for(int i=0;i<lang.size();i+=2) {
			this.lang.put(lang.get(i), lang.get(i+1));
		}
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
