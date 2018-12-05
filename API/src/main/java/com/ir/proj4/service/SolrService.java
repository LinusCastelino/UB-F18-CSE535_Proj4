package com.ir.proj4.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ir.proj4.model.Docs;
import com.ir.proj4.model.QueryData;
import com.ir.proj4.model.ReturnList;

@Service
public class SolrService {

	public ReturnList querySolr(String query, String dateFrom, String dateTo, String pageSize, String pageNo,
			String lang, String topic, String verified, String city)
			throws URISyntaxException, GeneralSecurityException, IOException {
		SentimentAnalysis.init();
		String q3;
		String url;

		// preprocessing on query params
		if (lang == null || lang.equals("") || lang.equals("\"\""))
			lang = "\"en\",\"es\",\"hi\",\"th\",\"fr\"";
		if (city == null || city.equals("") || city.equals("\"\""))
			city = "\"mexico%20city\",\"paris\",\"bangkok\",\"delhi\",\"nyc\"";
		else
			city = city.replace(" ", "%20");

		if (verified == null || verified.equals("") || verified.equals("\"\"") || verified.equals("false"))
			verified = "\"true\"%7C%7C\"false\"";

		if (topic == null || topic.equals("\"\"") || topic.equals(""))
			topic = "\"crime\",\"infra\",\"politics\",\"social%20unrest\",\"environment\"";
		else

			topic = topic.replace(" ", "%20");

		pageNo = Integer.toString((Integer.parseInt(pageNo)) * 10);

		q3 = URLEncoder.encode(query, "UTF-8");

		// hashtag not included
		// solr api query
		if ((dateFrom == null || dateFrom.equals("null") || dateFrom.equals(null) || dateFrom.equals("\"\"")
				|| dateFrom.equals(""))
				&& (dateTo == null || dateTo.equals("null") || dateTo.equals(null) || dateTo.equals("")
						|| dateTo.equals("\"\""))) {
			url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=topic&facet.field=verified&facet.field=lang&facet=on&qf=text&fq=topic:("
					+ topic + ")&fq=city:(" + city + ")&fq=verified:(" + verified + ")&fq=lang:(" + lang + ")&q=" + q3
					+ "&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="
					+ pageSize + "&start=" + pageNo + "&wt=json";
		} else {
			if (dateTo == null || dateTo.equals("") || dateTo.equals("\"\""))
				dateTo = "NOW";
			if (dateFrom == null || dateFrom.equals("\"\"") || dateFrom.equals(""))
				dateFrom = "2018-01-01T00:00:00Z";
			dateTo = URLEncoder.encode(dateTo, "UTF-8");
			dateFrom = URLEncoder.encode(dateFrom, "UTF-8");
			url = "http://18.191.170.212:8983/solr/IRF18P1/select?indent=true&deftype=edismax&facet.field=city&facet.field=lang&facet.field=topic&facet.field=verified&facet=on&qf=text&fq=tweet_date:["
					+ dateFrom + "%20TO%20" + dateTo + "]&fq=topic:(" + topic + ")&fq=verified:(" + verified
					+ ")&fq=city:(" + city + ")&fq=lang:(" + lang + ")&q=" + q3
					+ "&fl=tweet_date%2CuserName%2CuserProfile%2Ctext%2Clang%2Cverified%2Ctopic%2Ccity%2Cid_str&rows="
					+ pageSize + "&start=" + pageNo + "&wt=json";
		}

//	    System.out.println(url);

		// hitting solr API
		StringBuffer response = SolrResponse.solrResponse(url);
		// System.out.println(response);
		// input from solr will be processed now
		ObjectMapper obj_ObjectMapper = new ObjectMapper();
		QueryData obj_QueryData = new QueryData();
		obj_QueryData = obj_ObjectMapper.readValue(response.toString(), QueryData.class);

		for (Docs doc : obj_QueryData.getResponse().getDocs()) {
			doc.setSentiment(SentimentAnalysis.findSentiment(doc.getText().get(0)));
			List<String> temp = new ArrayList<String>();
			temp.add(doc.getTopic().get(0).substring(0, 1).toUpperCase() + doc.getTopic().get(0).substring(1));
			doc.setTopic(temp);
			temp = new ArrayList<String>();
			temp.add(doc.getCity().get(0).substring(0, 1).toUpperCase() + doc.getCity().get(0).substring(1));
			doc.setCity(temp);
		}
		ReturnList returnList = new ReturnList(obj_QueryData);
		return returnList;
	}

}