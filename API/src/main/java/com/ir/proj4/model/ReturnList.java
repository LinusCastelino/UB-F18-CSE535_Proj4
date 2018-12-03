package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ReturnList {
	
	
	ArrayList<Docs> tweets;
	HashMap<String,String> lang;
	HashMap<String,String> city;
	int numFound;
	HashMap<String,String> topic;
	HashMap<String,String> verified;
	
//	obj_QueryData.getResponse().getDocs(),obj_QueryData.getFacet_counts().getFacet_fields().getLang(),obj_QueryData.getFacet_counts().getFacet_fields().getCity(),obj_QueryData.getResponse().getNumFound()
	
//	public ReturnList(ArrayList<Docs> tweets,List<String> lang, List<String> city, int numFound,List<String> topic){
	public ReturnList(QueryData obj_QueryData) {
		this.tweets = obj_QueryData.getResponse().getDocs();
		this.lang = new HashMap<String,String>();
		this.city = new HashMap<String,String>();
		this.topic = new HashMap<String,String>();
		this.verified = new HashMap<String,String>();
		
		
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getLang().size();i+=2) {
			this.lang.put(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.city.put(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
		this.numFound = obj_QueryData.getResponse().getNumFound();
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getTopic().size();i+=2) {
			this.topic.put(obj_QueryData.getFacet_counts().getFacet_fields().getTopic().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getTopic().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getVerified().size();i+=2) {
			this.verified.put(obj_QueryData.getFacet_counts().getFacet_fields().getVerified().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getVerified().get(i+1));
		}
			
	}
		
	

	public HashMap<String, String> getVerified() {
		return verified;
	}

	public void setVerified(HashMap<String, String> verified) {
		this.verified = verified;
	}

	public HashMap<String, String> getTopic() {
		return topic;
	}

	public void setTopic(HashMap<String, String> topic) {
		this.topic = topic;
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
