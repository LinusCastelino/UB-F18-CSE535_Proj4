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
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.ir.proj4.model.Docs;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnList;

@Service
public class SolrService {
	
	public ReturnList querySolr(String query, String date,String pageSize, String pageNo, String lang,String topic, String city) throws URISyntaxException, GeneralSecurityException, IOException {
		
		//to work upon
		//date
//		String qen2;
//		String qes2;
//		String qhi2;
//		String qth2;
//		String qfr2;
//		String q2;
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

//		List<String> queryList = new ArrayList<String>();
//		queryList.add(query);
//		
//		//use google API to translate given query in 5 languages
//		TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyDqRLzA3Lq2zg81-mRlwxMdiW-QzXiA35I");
//		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//	    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null).setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();
//	    	    
//	    TranslationsListResponse qen = translate.translations().list(queryList, "en").execute();
//	    TranslationsListResponse qes = translate.translations().list(queryList, "es").execute();
//	    TranslationsListResponse qfr = translate.translations().list(queryList, "fr").execute();
//	    TranslationsListResponse qhi = translate.translations().list(queryList, "hi").execute();
//	    TranslationsListResponse qth = translate.translations().list(queryList, "th").execute();
//	    
//	    //append queries in different languages in one single string
//	    String detectedLang = qen.getTranslations().get(0).getDetectedSourceLanguage();
//	    qen2 = qen.getTranslations().get(0).get("translatedText").toString();
//	    qhi2 = qhi.getTranslations().get(0).get("translatedText").toString();
//	    qth2 = qth.getTranslations().get(0).get("translatedText").toString();
//	    qfr2 = qfr.getTranslations().get(0).get("translatedText").toString();
//	    qes2 = qes.getTranslations().get(0).get("translatedText").toString();
	    

	    
//	    if(detectedLang == "en")
//	    	qen2=qen2+"%5E100";
//	    else if(detectedLang == "es")
//	    	qes2=qes2+"%5E100";
//	    else if(detectedLang == "hi")
//	    	qhi2=qhi2+"%5E100";
//	    else if(detectedLang == "th")
//	    	qth2=qth2+"%5E100";
//	    else if(detectedLang == "fr")
//	    	qfr2=qfr2+"%5E100";
	    
//	    q2=qen2+"%7C%7C"+qth2+"%7C%7C"+qfr2+"%7C%7C"+qhi2+"%7C%7C"+qes2;
//	    System.out.println("query after deciding language is "+ q2);
	    //q3 = URLEncoder.encode(q2, "UTF-8");
	    
	    q3 = URLEncoder.encode(query, "UTF-8");
	    //System.out.println("query is : "+q3);
	    
	    // hashtag not included
	    //solr api query
	    if(date == null)
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=topic&facet.field=lang&facet=on&qf=text&fq=city:"+city+"&fq=lang:"+lang+"&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";
	    else
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=lang&facet.field=topic&facet=on&qf=text&fq=topic:"+topic+"&fq=city:"+city+"&fq=tweetDate:"+date+"&fq=lang:"+lang+"&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";

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
        	List<String> temp= new ArrayList<String>();
        	temp.add(doc.getTopic().get(0).substring(0, 1).toUpperCase() +doc.getTopic().get(0).substring(1));
        	doc.setTopic(temp);
        	temp.clear();
        	temp.add(doc.getCity().get(0).substring(0, 1).toUpperCase() +doc.getCity().get(0).substring(1));
        	doc.setCity(temp);
        }
//        ReturnList returnList = new ReturnList(obj_QueryData.getResponse().getDocs(),obj_QueryData.getFacet_counts().getFacet_fields().getLang(),obj_QueryData.getFacet_counts().getFacet_fields().getCity(),obj_QueryData.getResponse().getNumFound(),obj_QueryData.getFacet_counts().getFacet_fields().getTopic());
        ReturnList returnList = new ReturnList(obj_QueryData);
       //System.out.println(returnList.getTweets().get(0).getText());
        //final processed answer will be returned to the controller
        return returnList;
	}
	
	public String sentimentAnalysis(String tweetText) throws IOException {
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			  Document doc = Document.newBuilder()
			      .setContent(tweetText)
			      .setType(Type.PLAIN_TEXT)
			      .build();
			  AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
			  Sentiment sentiment = response.getDocumentSentiment();
			  if (sentiment == null) {
			    return "0";
			  } else {
//			    System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
//			    System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
				  if(sentiment.getScore()>0) {
					  return "1";
				  }
				  else if(sentiment.getScore()<0) {
					  return "-1";
				  }
				  else {
					  return "0";
				  }
			  }
//			  System.out.println(sentiment);
			}
		
	}
	
}