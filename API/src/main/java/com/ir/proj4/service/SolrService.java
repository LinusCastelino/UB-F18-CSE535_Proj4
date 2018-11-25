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
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnList;

@Service
public class SolrService {
	
	public ReturnList querySolr(String query) throws URISyntaxException, GeneralSecurityException, IOException {
		
		
		//to work on
		//select?facet.field=tweet_lang&facet=on&fq=city:%22%22nyc%22||%22delhi%22%22&fq=tweet_lang:%22%22hi%22||%22en%22%22&indent=on&q=:&wt=json
		
		
		
		//convert query in list
		List<String> queryList = new ArrayList<String>();
		queryList.add(query);
		
		//use google API to translate given query in 5 languages
		TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA");
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null).setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();
	    TranslationsListResponse qen = translate.translations().list(queryList, "en").execute();
	    TranslationsListResponse qes = translate.translations().list(queryList, "es").execute();
	    TranslationsListResponse qfr = translate.translations().list(queryList, "fr").execute();
	    TranslationsListResponse qhi = translate.translations().list(queryList, "hi").execute();
	    TranslationsListResponse qth = translate.translations().list(queryList, "th").execute();
	    
	    //append queries in different languages in one single string
	    String q2 =qen.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qhi.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qes.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qth.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qfr.getTranslations().get(0).get("translatedText").toString();
	    String q3 = URLEncoder.encode(q2, "UTF-8");
	    
	    //solr api query
	    String url = "http://localhost:8983/solr/ram1/select?facet.field=lang&facet=on&q="+q3+"&fl=tweet_date%2Ctext%2Clang%2Ctopic%2Ccity%2Cid%2Cscore&wt=json&indent=true&row=1000";
	    
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
        } 
        in .close();
        
        //input from solr will be processed now
        ObjectMapper obj_ObjectMapper = new ObjectMapper();
        QueryData obj_QueryData = new QueryData();
        obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
        ReturnList returnList = new ReturnList(obj_QueryData.getResponse().getDocs(),obj_QueryData.getFacet_counts().getFacet_fields().getLang());
        
        //final processed answer will be returned to the controller
        return returnList;
	}
	
}