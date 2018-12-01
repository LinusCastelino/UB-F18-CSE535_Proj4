package com.ir.proj4.model;

import java.util.HashMap;

public class ReturnStatisticsList {

	HashMap<String,String> lang;
	HashMap<String,String> city;
	HashMap<String,String> country;
	HashMap<String, String> cityToCountry = new HashMap<String,String>();
	
	public ReturnStatisticsList(QueryData obj_QueryData) {
		// TODO Auto-generated constructor stub
		cityToCountry.put("paris","France");
		cityToCountry.put("nyc","US");
		cityToCountry.put("delhi","India");
		cityToCountry.put("bangkok","Thailand");
		cityToCountry.put("mexico city","Mexico");
		this.lang = new HashMap<String,String>();
		this.city = new HashMap<String,String>();
		this.country = new HashMap<String,String>();
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getLang().size();i+=2) {
			this.lang.put(obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getLang().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.city.put(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.city.put(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
		for(int i=0;i<obj_QueryData.getFacet_counts().getFacet_fields().getCity().size();i+=2) {
			this.country.put(cityToCountry.get(obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i)), obj_QueryData.getFacet_counts().getFacet_fields().getCity().get(i+1));
		}
	}

	public HashMap<String, String> getCountry() {
		return country;
	}

	public void setCountry(HashMap<String, String> country) {
		this.country = country;
	}

	public HashMap<String, String> getLang() {
		return lang;
	}

	public void setLang(HashMap<String, String> lang) {
		this.lang = lang;
	}

	public HashMap<String, String> getCity() {
		return city;
	}

	public void setCity(HashMap<String, String> city) {
		this.city = city;
	}

}
