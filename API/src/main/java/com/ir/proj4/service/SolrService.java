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
	
	public ReturnList querySolr(String query, String date,String pageSize, String pageNo, String lang,String city) throws URISyntaxException, GeneralSecurityException, IOException {
		
		//to work upon
		//date
		String qen2;
		String qes2;
		String qhi2;
		String qth2;
		String qfr2;
		String q2;
		String q3;
		String url;
		
		
		//some preprocessing on query params
		if(lang==null || lang.equals("") || lang.equals("\"\""))
			lang="\"en\",\"es\",\"hi\",\"th\",\"fr\"";
		if(city==null || city.equals("") || city.equals("\"\"") )
			city="\"mexico%20city\",\"paris\",\"bangkok\",\"delhi\",\"nyc\"";
		else
			city=city.replace(" ", "%20");
		
		
		pageNo =Integer.toString((Integer.parseInt(pageNo))*10);
		
		
				
		//convert query in list
		List<String> queryList = new ArrayList<String>();
		queryList.add(query);
		
		//use google API to translate given query in 5 languages
		TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyDqRLzA3Lq2zg81-mRlwxMdiW-QzXiA35I");
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null).setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();
	    	    
	    TranslationsListResponse qen = translate.translations().list(queryList, "en").execute();
	    TranslationsListResponse qes = translate.translations().list(queryList, "es").execute();
	    TranslationsListResponse qfr = translate.translations().list(queryList, "fr").execute();
	    TranslationsListResponse qhi = translate.translations().list(queryList, "hi").execute();
	    TranslationsListResponse qth = translate.translations().list(queryList, "th").execute();
	    
	    //append queries in different languages in one single string
	    String detectedLang = qen.getTranslations().get(0).getDetectedSourceLanguage();
	    qen2 = qen.getTranslations().get(0).get("translatedText").toString();
	    qhi2 = qhi.getTranslations().get(0).get("translatedText").toString();
	    qth2 = qth.getTranslations().get(0).get("translatedText").toString();
	    qfr2 = qfr.getTranslations().get(0).get("translatedText").toString();
	    qes2 = qes.getTranslations().get(0).get("translatedText").toString();
	    
	    
	    if(detectedLang == "en")
	    	qen2=qen2+"^100";
	    else if(detectedLang == "es")
	    	qes2=qes2+"^100";
	    else if(detectedLang == "hi")
	    	qhi2=qhi2+"^100";
	    else if(detectedLang == "th")
	    	qth2=qth2+"^100";
	    else if(detectedLang == "fr")
	    	qfr2=qfr2+"^100";
	    
	    q2=qen2+"%7C%7C"+qth2+"%7C%7C"+qfr2+"%7C%7C"+qhi2+"%7C%7C"+qes2;
	    q3 = URLEncoder.encode(q2, "UTF-8");
	    
	    // hashtag not included
	    //solr api query
	    if(date == null)
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=lang&facet=on&qf=text&fq=city:"+city+"&fq=lang:"+lang+"&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";
	    else
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=lang&facet=on&qf=text&fq=city:"+city+"&fq=tweetDate:"+date+"&fq=lang:"+lang+"&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";
	    //System.out.println(url);   

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
        ReturnList returnList = new ReturnList(obj_QueryData.getResponse().getDocs(),obj_QueryData.getFacet_counts().getFacet_fields().getLang(),obj_QueryData.getFacet_counts().getFacet_fields().getCity(),obj_QueryData.getResponse().getNumFound());
        
        
       //System.out.println(returnList.getTweets().get(0).getText());
        //final processed answer will be returned to the controller
        return returnList;
	}
	
}