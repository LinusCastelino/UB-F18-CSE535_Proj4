package io.IRProject.springbootstarter.topic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

@Service
public class TopicService<JSONObject> {

	
	private static final String YOUR_API_KEY = null;
	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("Spring", "Spring Framework", "Spring Framework Description"),
			new Topic("Java", "Core Java", "Java Framework Description"),
			new Topic("JavaScript", "JavaScript Framework", "JavaScript Framework Description")
			));
	
	public List<Topic> getAllTopics(){
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
		
	}

	public void updateTopic(String id, Topic topic) {
		
		for(int i =0; i<topics.size();i++) {
			Topic t = topics.get(i);
			if(t.getId().equals(id)) {
				topics.set(i, topic);
				return;
			}
		}
		
		
	}

	public void deleteTopic(String id) {
		
		topics.removeIf(t->t.getId().equals(id));

		
	}

	
	/*
	public ResponseEntity<JSONObject> queryTranslate(String q) throws URISyntaxException {
		// TODO Auto-generated method stub
		String YOUR_API_KEY = "AIzaSyAHkcEjxhNKB2rlx8tG7MuAo0wRYuYMRsA";
		//URI yahoo = new URI("https://translation.googleapis.com/language/translate/v2?q="+q+"&target=es&format=text&key={"+YOUR_API_KEY+"}");
		//HttpHeaders httpHeaders = new HttpHeaders();
	    //httpHeaders.setLocation(yahoo);
	    //ResponseEntity<JSONObject> res = new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		// Instantiates a client
	    com.google.cloud.translate.Translate translate = TranslateOptions.getDefaultInstance().getService();
	    // The text to translate
	    //String text = q;
	    // spain
	    Translation translation1 =  translate.translate(q, TranslateOption.sourceLanguage("en"), TranslateOption.targetLanguage("es"));
	    GoogleAPI.setKey(YOUR_API_KEY);
	    //String qes=translation1.getTranslatedText();
		return res;
	}
*/
	
	
	
	
	
	
}
