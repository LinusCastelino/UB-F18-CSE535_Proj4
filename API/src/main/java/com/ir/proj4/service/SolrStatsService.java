package com.ir.proj4.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnStatisticsList;

@Service
public class SolrStatsService {

	public ReturnStatisticsList querySolr(String query, String lang, String topic, String verified, String city)
			throws URISyntaxException, GeneralSecurityException, IOException {

		String q3;
		String url;
		q3 = URLEncoder.encode(query, "UTF-8");

		url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=hashtags&facet.field=tweet_date&facet.field=topic&facet.field=verified&facet.field=lang&facet.limit=-1&facet=on&qf=text&q="
				+ q3 + "&fl=text%2Ctweet_date%2Ccity&wt=json";

//		System.out.println(url);

		// hitting solr API
		StringBuffer response = SolrResponse.solrResponse(url);
//        System.out.println(response);
		// input from solr will be processed now
		ObjectMapper obj_ObjectMapper = new ObjectMapper();
		QueryData obj_QueryData = new QueryData();
		obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);
//		url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=hashtags&facet.field=tweet_date&facet.field=topic&facet.field=verified&facet.field=lang&facet=on&qf=text&q="
//				+ q3 + "&fl=text%2Ctweet_date%2Ccity&rows=" + obj_QueryData.getResponse().getNumFound() + "&wt=json";
//		response = SolrResponse.solrResponse(url);
//		obj_ObjectMapper = new ObjectMapper();
//		obj_QueryData = new QueryData();
//		obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);

		ReturnStatisticsList returnStatList = new ReturnStatisticsList(obj_QueryData);
		return returnStatList;
	}

}
