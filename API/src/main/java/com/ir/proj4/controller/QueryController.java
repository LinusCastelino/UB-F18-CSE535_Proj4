package com.ir.proj4.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ir.proj4.model.ReturnList;
import com.ir.proj4.service.SolrService;

@RestController
public class QueryController<JSONObject> {
	
	@Autowired
	private SolrService solrService;
	
	@CrossOrigin
	@RequestMapping(value="select", method = RequestMethod.GET)

	public ReturnList getSolrResponse(@RequestParam(name="q") String query, @RequestParam(name="date", required=false) String date, @RequestParam(name="pageSize") String pageSize, @RequestParam(name="pageNo") String pageNo, @RequestParam(name="lang", required=false) String lang, @RequestParam(name="topic", required=false) String topic, @RequestParam(name="verified", required=false) String verified, @RequestParam(name="city", required=false) String city) throws URISyntaxException, GeneralSecurityException, IOException {
		//to work on
		//most common ISO Date Format yyyy-MM-dd
		
		return solrService.querySolr(query, date, pageSize, pageNo, lang, topic, verified, city);


	}	
}