package io.IRProject.springbootstarter.topic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.IOUtils;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequest;
import com.google.api.services.translate.TranslateRequestInitializer;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import antlr.ByteBuffer;
import javassist.Translator;

@Controller
@RestController
public class TopicController<JSONObject> {
	
	
	@Autowired
	private TopicService topicService;
	

	/*
	@RequestMapping("/ir/{q}")
	public ResponseEntity<Object> redirectToExternalUrl(@PathVariable String q) throws URISyntaxException {
		URI yahoo = new URI("http://localhost:8983/solr/ram1/select?q="+q+"&fl=tweet_date%2Centities.urls.url%2Ctext%2Clang%2Ctopic%2Ccity%2Cid%2Cscore&wt=json&indent=true&rows=15");
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(yahoo);
	    ResponseEntity<Object> res = new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	    return res;
	}
	
	*/
	
	
	
	@RequestMapping(value="ir", method = RequestMethod.GET)
	public @ResponseBody <T> ResponseEntity<Object> redirectToExternalUrl1(@RequestParam("q") List<String> q) throws URISyntaxException, GeneralSecurityException, IOException {
		//TranslateRequest<T> temp =setKey(java.lang.String "AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA");
		//="AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA";
		
		TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA");
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null)
	            .setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();


		//com.google.cloud.translate.Translate translate = TranslateOptions.getDefaultInstance().getService();
		//Translation translation = translate.translate(q, TranslateOption.sourceLanguage("en"),TranslateOption.targetLanguage("ru"));
	    TranslationsListResponse qen = translate.translations().list(q, "en").execute();
	    TranslationsListResponse qes = translate.translations().list(q, "es").execute();
	    TranslationsListResponse qfr = translate.translations().list(q, "fr").execute();
	    TranslationsListResponse qhi = translate.translations().list(q, "hi").execute();
	    TranslationsListResponse qth = translate.translations().list(q, "th").execute();
	    String q2 =qen.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qhi.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qes.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qth.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qfr.getTranslations().get(0).get("translatedText").toString();
	    //java.nio.ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(q2);
	    String q3 = URLEncoder.encode(q2, "UTF-8");
	    URI yahoo = new URI("http://localhost:8983/solr/ram1/select?facet.field=lang&facet=on&q="+q3+"&fl=tweet_date%2Centities.urls.url%2Ctext%2Clang%2Ctopic%2Ccity%2Cid%2Cscore&wt=json&indent=true");
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(yahoo);
	    ResponseEntity<Object> res = new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	    
	    return res;
	    
	    
	    
	    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@RequestMapping("/ir/{q}")
	public @ResponseBody <T> ResponseEntity<Object> redirectToExternalUrl1(@PathVariable List<String> q) throws URISyntaxException, GeneralSecurityException, IOException {
		//TranslateRequest<T> temp =setKey(java.lang.String "AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA");
		//="AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA";
		
		TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer("AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA");
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null)
	            .setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();


		//com.google.cloud.translate.Translate translate = TranslateOptions.getDefaultInstance().getService();
		//Translation translation = translate.translate(q, TranslateOption.sourceLanguage("en"),TranslateOption.targetLanguage("ru"));
	    TranslationsListResponse qen = translate.translations().list(q, "en").execute();
	    TranslationsListResponse qes = translate.translations().list(q, "es").execute();
	    TranslationsListResponse qfr = translate.translations().list(q, "fr").execute();
	    TranslationsListResponse qhi = translate.translations().list(q, "hi").execute();
	    TranslationsListResponse qth = translate.translations().list(q, "th").execute();
	    String q2 =qen.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qhi.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qes.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qth.getTranslations().get(0).get("translatedText").toString()+"%7C%7C"+qfr.getTranslations().get(0).get("translatedText").toString();
	    //java.nio.ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(q2);
	    String q3 = URLEncoder.encode(q2, "UTF-8");
	    URI yahoo = new URI("http://localhost:8983/solr/ram1/select?facet.field=lang&facet=on&q="+q3+"&fl=tweet_date%2Centities.urls.url%2Ctext%2Clang%2Ctopic%2Ccity%2Cid%2Cscore&wt=json&indent=true");
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(yahoo);
	    ResponseEntity<Object> res = new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	    
	    return res;
	    
	    
	    
	    
	}
	
*/
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
	
	
	
}
