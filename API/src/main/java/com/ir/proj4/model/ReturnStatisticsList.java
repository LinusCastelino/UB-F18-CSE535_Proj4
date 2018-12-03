package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnStatisticsList {

//	HashMap<String,String> lang;
	List<ArrayList<String>> lang;
	HashMap<String,String> city;
	HashMap<String,String> country;
	
	HashMap<String,String> hashtags;
	
	public ReturnStatisticsList(QueryData obj_QueryData) {
		HashMap<String, String> cityToCountry = new HashMap<String,String>();
		// TODO Auto-generated constructor stub
		cityToCountry.put("paris","France");
		cityToCountry.put("nyc","US");
		cityToCountry.put("delhi","India");
		cityToCountry.put("bangkok","Thailand");
		cityToCountry.put("hi","Hindi");
		cityToCountry.put("en","English");
		cityToCountry.put("th","Thai");
		cityToCountry.put("es","Spanish");
		cityToCountry.put("fr","French");
		
		this.lang = new ArrayList<ArrayList<String>>();
//		this.lang = new HashMap<String,String>();
		this.city = new HashMap<String,String>();
		this.country = new HashMap<String,String>();
		this.hashtags = new HashMap<String,String>();
		int hashtagCounts=20;
//		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getLang().size();i+=2) {
//			this.lang.put(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i+1));
//		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getLang().size();i+=2) {
			ArrayList<String> temp= new ArrayList<String>();
			temp.add(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i)));
			temp.add(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i+1));
			this.lang.add(temp);
//			this.country.put(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i)), obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i+1));
		}
		
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.city.put(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.city.put(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
//		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
//			this.country.put(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i)), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
//		}
		if(obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().size()<20) {
			hashtagCounts=obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().size();
		}
		for(int i=0;i<hashtagCounts;i+=2) {
			this.hashtags.put(obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().get(i+1));
		}
	}

	public HashMap<String, String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(HashMap<String, String> hashtags) {
		this.hashtags = hashtags;
	}

	public HashMap<String, String> getCountry() {
		return country;
	}

	public void setCountry(HashMap<String, String> country) {
		this.country = country;
	}

	public List<ArrayList<String>> getLang() {
		return lang;
	}

	public void setLang(HashMap<String, String> lang) {
		this.lang = (List<ArrayList<String>>) lang;
	}

	public HashMap<String, String> getCity() {
		return city;
	}

	public void setCity(HashMap<String, String> city) {
		this.city = city;
	}

}
