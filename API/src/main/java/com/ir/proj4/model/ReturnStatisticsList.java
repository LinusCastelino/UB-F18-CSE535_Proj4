package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ir.proj4.service.SentimentAnalysis;

public class ReturnStatisticsList {

//	HashMap<String,String> lang;
	List<ArrayList<Object>> lang;
	List<ArrayList<Object>> city;
	List<ArrayList<Object>> country;
	List<ArrayList<Object>> hashtags;
//	List<ArrayList<Object>> sentiment;
	List<ArrayList<Object>> tweet_date;
	

	public ReturnStatisticsList(QueryData obj_QueryData) {
//		SentimentAnalysis.init();
		HashMap<String, String> cityToCountry = new HashMap<String, String>();
		HashMap<String, String> cityRemane = new HashMap<String, String>();
//		HashMap<String, Integer> sentimentCount = new HashMap<String, Integer>();
		HashMap<String, Integer> tweetDtateCount = new HashMap<String, Integer>();
		// TODO Auto-generated constructor stub
		cityToCountry.put("paris", "France");
		cityToCountry.put("nyc", "US");
		cityToCountry.put("delhi", "India");
		cityToCountry.put("bangkok", "Thailand");
		cityToCountry.put("mexico city", "Mexico");

		cityToCountry.put("hi", "Hindi");
		cityToCountry.put("en", "English");
		cityToCountry.put("th", "Thai");
		cityToCountry.put("es", "Spanish");
		cityToCountry.put("fr", "French");

		cityRemane.put("paris", "Paris");
		cityRemane.put("nyc", "New York");
		cityRemane.put("bangkok", "Bangkok");
		cityRemane.put("delhi", "Delhi");
		cityRemane.put("mexico city", "Mexico City");

//		sentimentCount.put("Positive", 0);
//		sentimentCount.put("Neutral", 0);
//		sentimentCount.put("Negative", 0);

		this.lang = new ArrayList<ArrayList<Object>>();
		this.city = new ArrayList<ArrayList<Object>>();
		this.country = new ArrayList<ArrayList<Object>>();
		this.hashtags = new ArrayList<ArrayList<Object>>();
//		this.sentiment = new ArrayList<ArrayList<Object>>();
		this.tweet_date = new ArrayList<ArrayList<Object>>();

		int hashtagLimit = 20;
//		int sentimentsLimit = 10;

		for (int i = 0; i < obj_QueryData.getFacet_counts().getFacet_fields().getLang().size(); i += 2) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i)));
			temp.add(Integer.parseInt(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i + 1)));
			this.lang.add(temp);
		}
		for (int i = 0; i < obj_QueryData.getFacet_counts().getFacet_fields().getCity().size(); i += 2) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i)));
			temp.add(Integer.parseInt(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i + 1)));
			this.country.add(temp);
		}

		for (int i = 0; i < obj_QueryData.getFacet_counts().getFacet_fields().getCity().size(); i += 2) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(cityRemane.get(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i)));
			temp.add(Integer.parseInt(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i + 1)));
			this.city.add(temp);
		}

		if (obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().size() < 20) {
			hashtagLimit = obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().size();
		}

		for (int i = 0; i < hashtagLimit; i += 2) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add("#" + obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().get(i));
			temp.add(Integer.parseInt(obj_QueryData.getFacet_counts().getFacet_fields().getHashtags().get(i + 1)));
			this.hashtags.add(temp);
		}
//		calculating sentiment
//		if (obj_QueryData.getResponse().getNumFound() < 1000) {
//			sentimentsLimit = obj_QueryData.getResponse().getNumFound();
//		}
//		for (int i = 0; i < 100; i++) {
//			String analysisPerTweet = SentimentAnalysis
//					.findSentiment(obj_QueryData.getResponse().getDocs().get(i).getText().get(0));
//			sentimentCount.put(analysisPerTweet, sentimentCount.get(analysisPerTweet) + 1);
//		}
//		for (String keys : sentimentCount.keySet()) {
//			ArrayList<Object> temp = new ArrayList<Object>();
//			temp.add(keys);
//			temp.add(sentimentCount.get(keys));
//			this.sentiment.add(temp);
//		}
		for (int i = 0; i < obj_QueryData.getFacet_counts().getFacet_fields().getTweet_date().size(); i+=2) {
			String subStringDate = obj_QueryData.getFacet_counts().getFacet_fields().getTweet_date().get(i).substring(0, 10);
			int count = tweetDtateCount.containsKey(subStringDate) ? tweetDtateCount.get(subStringDate) : 0;
			tweetDtateCount.put(subStringDate, count + Integer.parseInt(obj_QueryData.getFacet_counts().getFacet_fields().getTweet_date().get(i + 1)));
			
		}
		for (String keys : tweetDtateCount.keySet()) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(keys);
			temp.add(tweetDtateCount.get(keys));
			this.tweet_date.add(temp);
		}
		

	}

	public List<ArrayList<Object>> getTweet_date() {
		return tweet_date;
	}

	public void setTweet_date(List<ArrayList<Object>> tweet_date) {
		this.tweet_date = tweet_date;
	}

//	public List<ArrayList<Object>> getSentiment() {
//		return sentiment;
//	}
//
//	public void setSentiment(List<ArrayList<Object>> sentiment) {
//		this.sentiment = sentiment;
//	}

	public List<ArrayList<Object>> getLang() {
		return lang;
	}

	public void setLang(List<ArrayList<Object>> lang) {
		this.lang = lang;
	}

	public List<ArrayList<Object>> getCity() {
		return city;
	}

	public void setCity(List<ArrayList<Object>> city) {
		this.city = city;
	}

	public List<ArrayList<Object>> getCountry() {
		return country;
	}

	public void setCountry(List<ArrayList<Object>> country) {
		this.country = country;
	}

	public List<ArrayList<Object>> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<ArrayList<Object>> hashtags) {
		this.hashtags = hashtags;
	}

}
