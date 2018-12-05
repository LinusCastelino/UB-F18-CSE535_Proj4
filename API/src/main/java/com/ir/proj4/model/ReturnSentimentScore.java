package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ir.proj4.service.SentimentAnalysis;

public class ReturnSentimentScore {
	
	List<ArrayList<Object>> sentiment;
	
	public ReturnSentimentScore(QueryData obj_QueryData) {
		SentimentAnalysis.init();
		HashMap<String, Integer> sentimentCount = new HashMap<String, Integer>();
		
		long end = System.currentTimeMillis()+28000;
		
		sentimentCount.put("Positive", 0);
		sentimentCount.put("Neutral", 0);
		sentimentCount.put("Negative", 0);
		
		this.sentiment = new ArrayList<ArrayList<Object>>();
		
		int sentimentsLimit = obj_QueryData.getResponse().getNumFound();
		
//		calculating sentiment
//		if (obj_QueryData.getResponse().getNumFound() < 100) {
//			sentimentsLimit = obj_QueryData.getResponse().getNumFound();
//		}
		for (int i = 0; i < sentimentsLimit && System.currentTimeMillis() < end; i++) {
			String analysisPerTweet = SentimentAnalysis
					.findSentiment(obj_QueryData.getResponse().getDocs().get(i).getText().get(0));
			sentimentCount.put(analysisPerTweet, sentimentCount.get(analysisPerTweet) + 1);
		}
		for (String keys : sentimentCount.keySet()) {
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(keys);
			temp.add(sentimentCount.get(keys));
			this.sentiment.add(temp);
		}
	}

	public List<ArrayList<Object>> getSentiment() {
		return sentiment;
	}

	public void setSentiment(List<ArrayList<Object>> sentiment) {
		this.sentiment = sentiment;
	}

}
