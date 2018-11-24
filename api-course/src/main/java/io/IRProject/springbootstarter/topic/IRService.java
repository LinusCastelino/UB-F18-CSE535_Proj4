package io.IRProject.springbootstarter.topic;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IRService {

	public void query() {
		final String uri = "http://localhost:8983/solr/ram1/select?q=murder&fl=id%2Cscore&wt=json&indent=true&rows=1000";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);

	    System.out.println(result);
	}

}
