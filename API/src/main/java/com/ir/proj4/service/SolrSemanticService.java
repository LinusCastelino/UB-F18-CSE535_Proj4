package com.ir.proj4.service;

import java.io.IOException;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnSentimentScore;

@Service
public class SolrSemanticService {

	public ReturnSentimentScore querySolr(String query) throws IOException {
		
		String q3;
		String url;
		q3 = URLEncoder.encode(query, "UTF-8");

		url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&qf=text&q="
				+ q3 + "&fl=text&wt=json";

//		System.out.println(url);

		// hitting solr API
		StringBuffer response = SolrResponse.solrResponse(url);
//        System.out.println(response);
		// input from solr will be processed now
		ObjectMapper obj_ObjectMapper = new ObjectMapper();
		QueryData obj_QueryData = new QueryData();
		obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
		url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&qf=text&q="
				+ q3 + "&fl=text&rows=" + obj_QueryData.getResponse().getNumFound() + "&wt=json";
		response = SolrResponse.solrResponse(url);
		obj_ObjectMapper = new ObjectMapper();
		obj_QueryData = new QueryData();
		obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
//		System.out.println(response);
		ReturnSentimentScore returnStatList = new ReturnSentimentScore(obj_QueryData);
		return returnStatList;
	}

}
