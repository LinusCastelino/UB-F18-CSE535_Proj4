package com.ir.proj4.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ir.proj4.model.Docs;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnList;
import com.ir.proj4.model.ReturnStatisticsList;

@Service
public class SolrStatsService {

	public ReturnStatisticsList querySolr(String query, String lang,String topic,String verified, String city) throws URISyntaxException, GeneralSecurityException, IOException {
		SentimentAnalysis.init();
	
		String q3;
		String url;
		
		
		//some preprocessing on query params
//		if(lang==null || lang.equals("") || lang.equals("\"\""))
//			lang="\"en\",\"es\",\"hi\",\"th\",\"fr\"";
//		if(city==null || city.equals("") || city.equals("\"\"") )
//			city="\"mexico%20city\",\"paris\",\"bangkok\",\"delhi\",\"nyc\"";
//		else
//			city=city.replace(" ", "%20");
//		
//		System.out.println("verified is : "+ verified);
//		if(verified==null || verified.equals("") || verified.equals("false"))
//			verified="\"true\"%7C%7C\"false\"";
//		
		
	
		
	    q3 = URLEncoder.encode(query, "UTF-8");
	    //System.out.println("query is : "+q3);
	    
	    // hashtag not included
	    //solr api query
	    
	    url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=hashtags&facet.field=topic&facet.field=verified&facet.field=lang&facet=on&qf=text&q="+q3+"&fl=text%2Ctweet_date%2Ccity&wt=json";
	    
	    System.out.println(url);   

	    //hitting solr API

	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        
        //reading input from solr
        //each line from input from solr will get append in "response" buffer
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
//            System.out.println(inputLine);
        } 
        in .close();
        
        System.out.println(response);
        
        //input from solr will be processed now
        ObjectMapper obj_ObjectMapper = new ObjectMapper();
        QueryData obj_QueryData = new QueryData();
        obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
        for(Docs doc : obj_QueryData.getResponse().getDocs() ) {
//        	doc.setSemanticScore(sentimentAnalysis(doc.getText().get(0))); 
        	doc.setSentiment(SentimentAnalysis.findSentiment(doc.getText().get(0)));
        	List<String> temp= new ArrayList<String>();
//        	temp.add(doc.getTopic().get(0).substring(0, 1).toUpperCase() +doc.getTopic().get(0).substring(1));
//        	doc.setTopic(temp);
//        	temp = new ArrayList<String>();
//        	temp.add(doc.getCity().get(0).substring(0, 1).toUpperCase() +doc.getCity().get(0).substring(1));
//        	doc.setCity(temp);
        }
//        ReturnList returnList = new ReturnList(obj_QueryData.getResponse().getDocs(),obj_QueryData.getFacet_counts().getFacet_fields().getLang(),obj_QueryData.getFacet_counts().getFacet_fields().getCity(),obj_QueryData.getResponse().getNumFound(),obj_QueryData.getFacet_counts().getFacet_fields().getTopic());
        ReturnStatisticsList returnStatList = new ReturnStatisticsList(obj_QueryData);
       //System.out.println(returnList.getTweets().get(0).getText());
        //final processed answer will be returned to the controller
        return returnStatList;
	}

}
