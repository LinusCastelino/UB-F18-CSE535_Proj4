package com.ir.proj4.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="ir", method = RequestMethod.GET)
	public ReturnList redirectToExternalUrl1(@RequestParam("q") String query) throws URISyntaxException, GeneralSecurityException, IOException {
//		System.out.println("Inside");
		return solrService.querySolr(query);

	}	
}