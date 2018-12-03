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
	
	public ReturnList querySolr(String query, String dateFrom, String dateTo, String pageSize, String pageNo, String lang,String topic,String verified, String city) throws URISyntaxException, GeneralSecurityException, IOException {
		SentimentAnalysis.init();
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
		
		//System.out.println("verified is : "+ verified);
		if(verified==null || verified.equals("") || verified.equals("\"\"") || verified.equals("false"))
			verified="\"true\"%7C%7C\"false\"";
		
		if(topic==null || topic.equals("\"\"") || topic.equals(""))
			topic = "\"crime\",\"infra\",\"politics\",\"social%20unrest\",\"environment\"";
		else

			topic=topic.replace(" ", "%20");
		

		pageNo =Integer.toString((Integer.parseInt(pageNo))*10);
		
			
	    
	    q3 = URLEncoder.encode(query, "UTF-8");
	    //System.out.println("query is : "+q3);
	    
	    //System.out.println("datetesting, : "+dateTo.equals(null));
	    
	    
	    // hashtag not included
	    //solr api query
	    if((dateFrom==null || dateFrom.equals("null") || dateFrom.equals(null) || dateFrom.equals("\"\"") || dateFrom.equals("")) && (dateTo == null || dateTo.equals("null") || dateTo.equals(null) || dateTo.equals("") || dateTo.equals("\"\""))) {
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=topic&facet.field=verified&facet.field=lang&facet=on&qf=text&fq=topic:("+topic+")&fq=city:("+city+")&fq=verified:("+verified+")&fq=lang:("+lang+")&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";
	    }
	    	else {
	    	if(dateTo==null || dateTo.equals("") || dateTo.equals("\"\""))
				dateTo="NOW";
			if(dateFrom==null || dateFrom.equals("\"\"") || dateFrom.equals(""))
				dateFrom="2018-01-01T00:00:00Z";
			dateTo=URLEncoder.encode(dateTo, "UTF-8");
			dateFrom=URLEncoder.encode(dateFrom, "UTF-8");
	    	url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=lang&facet.field=topic&facet.field=verified&facet=on&qf=text&fq=tweet_date:["+dateFrom+"%20TO%20"+dateTo+"]&fq=topic:("+topic+")&fq=verified:("+verified+")&fq=city:("+city+")&fq=lang:("+lang+")&q="+q3+"&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="+pageSize+"&start="+pageNo+"&wt=json";
	    }
	    

//	    System.out.println(url);

	    //hitting solr API

	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	       
        con.setRequestMethod("GET");
        
        
        
        //reading input from solr
        //each line from input from solr will get append in "response" buffer
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        //System.out.println("test1");
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
//            System.out.println(inputLine);
        } 
        in .close();
        

        //System.out.println(response);

        
        //input from solr will be processed now
        ObjectMapper obj_ObjectMapper = new ObjectMapper();
        QueryData obj_QueryData = new QueryData();
        obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
        for(Docs doc : obj_QueryData.getResponse().getDocs() ) {
        	if(!((doc.getLang().get(0).equals("hi"))||(doc.getLang().get(0).equals("th")))) {
//        		doc.setSentiment(sentimentAnalysis(doc.getText().get(0))); 
        		
        	}
        	doc.setSentiment(SentimentAnalysis.findSentiment(doc.getText().get(0)));
        	
        	List<String> temp= new ArrayList<String>();
        	temp.add(doc.getTopic().get(0).substring(0, 1).toUpperCase() +doc.getTopic().get(0).substring(1));
        	doc.setTopic(temp);
        	temp = new ArrayList<String>();
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
			    return "neutral";
			  } else {
//			    System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
//			    System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
				  if(sentiment.getScore()>0) {
					  return "positive";
				  }
				  else if(sentiment.getScore()<0) {
					  return "negative";
				  }
				  else {
					  return "neutral";
				  }
			  }
//			  System.out.println(sentiment);
			}
		
	}
	
}